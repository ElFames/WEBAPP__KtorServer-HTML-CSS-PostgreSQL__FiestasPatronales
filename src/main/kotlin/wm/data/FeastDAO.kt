package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*

class FeastDAO {
    val urlMaps = "https://www.google.com/maps/embed?pb="

    fun addCity(name: String) {
        transaction {
            City.new {
                this.name = name
            }
        }
    }

    fun addTown(name: String, city: String) {
        transaction {
            Town.new {
                this.name = name
                this.city = city
            }
        }
    }

    fun searchFeast(name: String): List<Feast>? {
        var feasts: List<Feast>? = null
        val regex = Regex(".*$name.*")
        transaction {
            feasts = Feast.find { Feasts.name like regex.pattern }.toList()
        }
        return feasts
    }

    fun addFeast(feastDataInList: Map<String, String>) {
        transaction {
            Feast.new {
                name = feastDataInList["name"]!!
                dates = feastDataInList["dates"]!!
                city = feastDataInList["city"]!!
                town = feastDataInList["town"]!!
                description = feastDataInList["description"]!!
                image = feastDataInList["image"]!!
            }
        }
    }

    fun checkCity(cityName: String): Boolean {
        var city: SizedIterable<City>? = null
        transaction {
            city = City.find { Citys.name eq cityName }
        }
        return city != null
    }
    fun checkTown(townName: String): Boolean {
        var town: SizedIterable<City>? = null
        transaction {
            town = City.find { Citys.name eq townName }
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
        if (checkCity(city))
            addCity(city)
        if (checkTown(town))
            addTown(town,city)
    }
}