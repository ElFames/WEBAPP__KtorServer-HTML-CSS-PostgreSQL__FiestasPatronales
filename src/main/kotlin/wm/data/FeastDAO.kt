package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*
import java.lang.Exception

class FeastDAO {
    val urlMaps = "https://www.google.com/maps/embed?pb="

    fun addCity(name: String) {
        transaction {
            City.new {
                this.name = name
            }
        }
    }

    fun addTown(name: String, cityName: String) {
        val city = getCity(cityName)
        transaction {
            Town.new {
                this.name = name
                this.city = city?.id ?:
                throw Exception("City not found")
            }
        }
    }

    private fun getCity(cityName: String): City? {
        return transaction {
            City.find { Citys.name eq cityName }.firstOrNull()
        }
    }

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
                city = getCity(feastDataInList["city"]!!)!!.id
                town = getTown(feastDataInList["town"]!!)!!.id
                description = feastDataInList["description"]!!
                image = feastDataInList["image"]!!
            }
        }
    }

    private fun getTown(townName: String): Town? {
        return transaction {
            Town.find { Towns.name eq townName }.firstOrNull()
        }
    }

    fun checkCity(cityName: String): Boolean {
       val city = transaction {
            City.find { Citys.name eq cityName }.firstOrNull()
        }
        return city != null
    }
    fun checkTown(townName: String): Boolean {
        val town = transaction {
            Town.find { Towns.name eq townName }.firstOrNull()
        }
        return town != null
    }

    fun getAllFeasts(): SizedIterable<Feast>? {
        var feastList: SizedIterable<Feast>? = null
        transaction {
            feastList = Feast.all()
        }
        return feastList
    }

    fun createCityAndTownIfNotExists(town: String, city: String) {
        if (!checkCity(city))
            addCity(city)
        if (!checkTown(town))
            addTown(town,city)
    }

    fun getAllCitys(): SizedIterable<City> {
        return transaction {
            City.all()
        }
    }
}