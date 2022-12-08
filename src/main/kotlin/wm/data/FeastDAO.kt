package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*

class FeastDAO(private val cityDAO: CityDAO, private val townDAO: TownDAO) {
    var searchResult = mutableListOf<Feast?>()

    fun searchFeast(name: String): MutableList<Feast> {
        searchResult.removeAll { true }
        val regex = Regex(".*$name.*")
        val resultList = mutableListOf<Feast>()
        transaction {
            getAllFeasts().forEach {
                println(regex in it.name)
                println("$regex ${it.name}")
                if (regex in it.name || regex in it.city.name || regex in it.town.name)
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

    fun getFeastById(feastId: Int?): Feast? {
        return transaction {
            Feast.find { Feasts.id eq feastId }.firstOrNull()
        }
    }

    fun getFeast(feastName: String): Feast? {
        return transaction {
            Feast.find { Feasts.name eq feastName }.firstOrNull()
        }
    }
}
