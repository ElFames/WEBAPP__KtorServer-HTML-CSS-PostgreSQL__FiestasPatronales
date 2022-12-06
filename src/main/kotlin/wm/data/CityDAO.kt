package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.City
import wm.models.Citys

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
    fun getCityById(cityId: Int): City? {
        return transaction {
            City.find { Citys.id eq cityId }.firstOrNull()
        }
    }
}