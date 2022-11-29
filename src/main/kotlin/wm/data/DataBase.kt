package wm.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.Citys
import wm.models.Feasts
import wm.models.Towns

object DataBase {
    fun init() {
        val driver = "org.postgresql.Driver"
        val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
        val database = Database.connect( dbUrl, driver, user = "postgres", password = "")
        transaction(database) {
            SchemaUtils.create(Citys, Towns,Feasts)
        }
    }
}