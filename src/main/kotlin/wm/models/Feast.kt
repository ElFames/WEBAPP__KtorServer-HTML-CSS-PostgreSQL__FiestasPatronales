package wm.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Feast(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Feast>(Feasts)
    var name by Feasts.name
    var dates by Feasts.dates
    var likes by Feasts.likes
    var city by Feasts.city
    var town by Feasts.town
    var image by Feasts.image
    var description by Feasts.description
}