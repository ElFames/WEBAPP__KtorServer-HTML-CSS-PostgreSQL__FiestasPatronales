package wm.data

import wm.models.*

class FeastDAO {
    fun addFeast(name: String, city: String, town: String, dates: String, likes: Int, image: String, description: String) {
        val feast = Feast.new {
            this.name = name
            this.city = city
            this.town = town
            this.dates = dates
            this.likes = likes
            this.image = image
            this.description = description
        }
        DataBase.addFeast(feast)
    }
    fun addCity(name: String) {
        val city = City.new {
            this.name = name
        }
        DataBase.addCity(city)
    }
    fun addTown(name: String, city: String) {
        val town = Town.new {
            this.name = name
            this.city = city
        }
        DataBase.addTown(town)
    }
    fun addUser(name: String, password: String, email: String, role: String) {
        val user = User.new {
            this.name = name
            this.password = password
            this.email = email
            this.role = role
        }
        DataBase.addUser(user)
    }

    fun searchFeast(name: String): List<Feast> {
        val regex = Regex(".*$name.*")
        return Feast.find { Feasts.name like regex.pattern }.toList()
    }
}