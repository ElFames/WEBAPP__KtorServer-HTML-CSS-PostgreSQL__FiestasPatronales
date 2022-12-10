package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.city.City
import wm.models.city.Citys
import wm.models.city.JsonCity
import wm.models.feast.JsonFeast

class CityDAO {

    fun getAllCitys(): SizedIterable<City> {
        return transaction {
            City.all()
        }
    }
    fun addCity(name: String) {
        transaction {
            City.new {
                this.name = name
            }
        }
    }
    fun checkCity(cityName: String): Boolean {
        return transaction {
            City.find { Citys.name eq cityName }.firstOrNull()
        } != null
    }

    fun getCityByName(cityName: String): City? {
        return transaction {
            City.find { Citys.name eq cityName }.firstOrNull()
        }
    }

    fun getJsonCity(city: City): JsonCity {
        return transaction {
            JsonCity(city.id.value,city.name)
        }
    }

    fun getJsonsCitys(): MutableList<JsonCity> {
        val jsonCityList = mutableListOf<JsonCity>()
        transaction {
            getAllCitys().forEach {
                jsonCityList.add(
                    JsonCity(
                        it.id.value,
                        it.name
                    )
                )
            }
        }
        return jsonCityList
    }
}