package wm.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*

object DataBase {
    fun init() {
        val driver = "org.postgresql.Driver"
        val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
        val database = Database.connect( dbUrl, driver, user = "postgres", password = "")
        transaction(database) {
            SchemaUtils.create(Citys, Towns,Feasts)
        }
    }
    fun addFeast(feast: Feast) {
        transaction {
            Feasts.insert {
                it[city] = feast.city
                it[town] = feast.town
                it[name] = feast.name
                it[dates] = feast.dates
                it[likes] = feast.likes
                it[image] = feast.image
                it[description] = feast.description
            }
        }
    }
    fun addCity(city: City) {
        transaction {
            Citys.insert {
                it[name] = city.name
            }
        }
    }
    fun addTown(town: Town) {
        transaction {
            Towns.insert {
                it[name] = town.name
                it[city] = town.city
            }
        }
    }
    fun addUser(user: User) {
        transaction {
            Users.insert {
                it[name] = user.name
                it[password] = user.password
                it[email] = user.email
                it[role] = user.role
            }
        }
    }

}