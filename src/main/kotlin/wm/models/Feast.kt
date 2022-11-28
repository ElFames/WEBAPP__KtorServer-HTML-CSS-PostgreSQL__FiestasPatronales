package wm.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
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

class Feast(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Feast>(Feasts)
    val name by Feasts.name
    val dates by Feasts.dates
    val likes by Feasts.likes
    val city by Feasts.city
    val town by Feasts.town
    val image by Feasts.image
    val description by Feasts.description
}