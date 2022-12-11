package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.town.JsonTown
import wm.models.town.Town
import wm.models.town.Towns

class TownDAO(private val cityDAO: CityDAO) {
    fun checkTown(townName: String) =
        transaction {
            Town.find { Towns.name eq townName }.firstOrNull()
        } != null
    fun getTown(townName: String): Town? {
        return transaction {
            Town.find { Towns.name eq townName}.firstOrNull()
        }
    }
    fun getTownById(townId: Int): Town? {
        return transaction {
            Town.find { Towns.id eq townId }.firstOrNull()
        }
    }
    fun getAllTowns(): SizedIterable<Town> {
        return transaction {
            Town.all()
        }
    }
    fun getJsonTown(town: Town): JsonTown {
        return transaction {
            JsonTown(town.id.value,town.name, cityDAO.getJsonCity(town.city))
        }

    }
    fun getJsonsTowns(): MutableList<JsonTown> {
        val jsonTownList = mutableListOf<JsonTown>()
        transaction {
            getAllTowns().forEach {
                jsonTownList.add(
                    JsonTown(
                        it.id.value,
                        it.name,
                        cityDAO.getJsonCity(it.city)
                    )
                )
            }
        }
        return jsonTownList
    }
    fun addTown(name: String, cityName: String) {
        val city = cityDAO.getCityByName(cityName) ?: return // fk
        transaction {
            Town.new {
                this.name = name
                this.city = city
            }
        }
    }
    fun getTownsByCityName(cityName: String) =
        transaction {
            Town.find { Towns.cityId.eq(cityDAO.getCityByName(cityName)!!.id)}
        }
}