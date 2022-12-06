package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*

class FeastDAO(private val cityDAO: CityDAO, private val townDAO: TownDAO) {

    fun searchFeast(name: String): MutableList<String> {
        val regex = Regex(".*$name.*")
        val resultList = mutableListOf<String>()
        transaction {
            getAllFeasts().forEach {
                if (regex.find(it.name)?.value != null)
                    resultList.add(regex.find(it.name)!!.value)
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
