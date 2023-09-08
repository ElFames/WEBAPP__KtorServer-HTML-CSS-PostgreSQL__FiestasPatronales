package wm.data.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.models.city.Citys
import wm.data.models.feast.Feasts
import wm.data.models.town.Towns
import wm.data.models.user.Users

object DataBase {
    fun init() {
        val driver = "org.postgresql.Driver"
        val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
        val database = Database.connect(dbUrl, driver, user="postgres", password="")
        transaction(database) {
            SchemaUtils.create(Citys, Towns, Feasts, Users)
        }
    }
}