package wm.data

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.city.City
import wm.models.city.Citys
import wm.models.city.JsonCity

class CityDAO {
    // para que se añadan algunas ciudades al iniciar el servidor por primera vez ya que la base de datos no esta publicada
    val defaultCitys = mutableListOf(
        Pair("Barcelona","!1m18!1m12!1m3!1d197294.4734084018!2d-0.5015960431457369!3d39.40770125143522!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd604f4cf0efb06f%3A0xb4a351011f7f1d39!2sValencia!5e0!3m2!1ses!2ses!4v1670611100606!5m2!1ses!2ses"),
        Pair("Valencia","!1m16!1m12!1m3!1d761939.0669410746!2d1.5082626686376353!3d41.756661407921655!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1s"),
        Pair("Sevilla","Barcelona!5e0!3m2!1ses!2ses!4v1670611631806!5m2!1ses!2ses"),
        Pair("Madrid","!1m18!1m12!1m3!1d101459.59291838905!2d-6.025098426770423!3d37.3753500988125!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3"),
        Pair("Zaragoza","m3!1m2!1s0xd126c1114be6291%3A0x34f018621cfe5648!2sSevilla!5e0!3m2!1ses!2ses!4v1670611721476!5m2!1ses!2ses"),
        Pair("Cadiz","!1m18!1m12!1m3!1d194348.13981247725!2d-3.8196212499898277!3d40.437869758801256!2m3!1f0!2f0!3f0!3m2!1i1024!2")
    )

    fun getAllCitys(): SizedIterable<City> {
        return transaction {
            City.all()
        }
    }
    fun addCity(name: String, location: String? = null) {
        if (!checkCity(name)) {
            transaction {
                City.new {
                    this.name = name
                    this.location = location
                }
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

    // se ha creado una clase JsonCity porque la clase City al heredar de una IntTable
    // no puede ser serializable para mostrar en los endpoints que devuelven jsons
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