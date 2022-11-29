package wm.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Feasts: IntIdTable() {
    val name = varchar("name",50).uniqueIndex()
    val dates = varchar("dates",50)
    val likes = integer("likes")
    val city = reference("name", Citys.name)
    val town = reference("name", Towns.name)
    val image = varchar("image",50)
    val description = varchar("description",500)
}