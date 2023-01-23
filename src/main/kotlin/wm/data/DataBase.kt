package wm.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.city.Citys
import wm.models.feast.Feasts
import wm.models.town.Towns
import wm.models.user.Users

object DataBase {
    fun init() {
        val driver = "org.postgresql.Driver"
        val dbUrl = "jdbc:postgresql://localhost:5432/fiestasPatronales"
        val database = Database.connect(dbUrl, driver, user="sjo", password="")
        transaction(database) {
            SchemaUtils.create(Citys, Towns, Feasts, Users)
        }
    }
}