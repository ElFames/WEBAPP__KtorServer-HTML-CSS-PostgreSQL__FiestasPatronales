package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.feast.Feast
import wm.models.feast.Feasts
import wm.models.feast.JsonFeast

class FeastDAO(private val cityDAO: CityDAO, private val townDAO: TownDAO) {
    var searchResult = mutableListOf<Feast?>()

    fun searchFeast(name: String): MutableList<Feast> {
        searchResult.removeAll { true }
        val regex = Regex(".*${name.lowercase()}.*")
        val resultList = mutableListOf<Feast>()
        transaction {
            getAllFeasts().forEach {
                if (regex in it.name.lowercase() || regex in it.city.name.lowercase() || regex in it.town.name.lowercase())
                    resultList.add(it)
            }
        }
        return resultList
    }

    fun addFeast(feastDataInList: Map<String, String>) {
        transaction {
            Feast.new {
                name = feastDataInList["name"]!!
                dates = feastDataInList["dates"]!!
                city = cityDAO.getCityByName(feastDataInList["city"]!!)!!
                town = townDAO.getTown(feastDataInList["town"]!!)!!
                description = feastDataInList["description"]!!
                image = feastDataInList["image"]!!
            }
        }
    }

    fun getAllFeasts(): SizedIterable<Feast> {
        return transaction {
            Feast.all()
        }
    }
    fun getJsonsFeasts(): MutableList<JsonFeast> {
        val jsonFeastList = mutableListOf<JsonFeast>()
        transaction {
            getAllFeasts().forEach {
                jsonFeastList.add(
                    JsonFeast(
                        it.id.value,
                        it.name,
                        it.dates,
                        it.description,
                        cityDAO.getJsonCity(it.city),
                        townDAO.getJsonTown(it.town),
                        it.image,it.likes.toString()
                    )
                )
            }
        }
        return jsonFeastList
    }
    fun getFeastById(feastId: Int?): Feast? {
        return transaction {
            Feast.find { Feasts.id eq feastId }.firstOrNull()
        }
    }

    fun getFeastByName(feastName: String): Feast? {
        return transaction {
            Feast.find { Feasts.name eq feastName }.firstOrNull()
        }
    }

    fun getJsonFeast(id: Int): JsonFeast {
        val feast = getFeastById(id)!!
        return transaction {
            JsonFeast(
                feast.id.value,
                feast.name,
                feast.dates,
                feast.description,
                cityDAO.getJsonCity(feast.city),
                townDAO.getJsonTown(feast.town),
                feast.image,
                feast.likes.toString()
            )
        }
    }
}
