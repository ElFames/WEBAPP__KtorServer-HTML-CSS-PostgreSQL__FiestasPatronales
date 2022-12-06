package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*

class FeastDAO(private val cityDAO: CityDAO, private val townDAO: TownDAO) {

    fun searchFeast(name: String): List<Feast> {
        val regex = Regex(".*$name.*")
        return transaction {
            Feast.find { Feasts.name like regex.pattern }.toList()
        }
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

    fun createFKsIfNotExist(town: String, city: String) {
        if (!cityDAO.checkCity(city))
            cityDAO.addCity(city)
        if (!townDAO.checkTown(town))
            townDAO.addTown(town,city)
    }

    fun getFeastById(feastId: Int?): Feast? {
        return transaction {
            Feast.find { Feasts.id eq feastId }.firstOrNull()
        }
    }
}
