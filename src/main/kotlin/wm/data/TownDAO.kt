package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.City
import wm.models.Citys
import wm.models.Town
import wm.models.Towns
import java.lang.Exception

class TownDAO(private val cityDAO: CityDAO) {

    fun checkTown(townName: String): Boolean {
        val town = transaction {
            Town.find { Towns.name eq townName }.firstOrNull()
        }
        return town != null
    }

    fun addTown(name: String, cityName: String) {
        val city = cityDAO.getCityByName(cityName) ?: return
        transaction {
            Town.new {
                this.name = name
                this.city = city
            }
        }
    }

    fun getTown(townName: String): Town? {
        return transaction {
            Town.find { Towns.name eq townName }.firstOrNull()
        }
    }
    fun getAllTowns(): SizedIterable<Town> {
        return transaction {
            Town.all()
        }
    }

    fun getTownById(townId: Int): Town? {
        return transaction {
            Town.find { Towns.id eq townId }.firstOrNull()
        }
    }
}