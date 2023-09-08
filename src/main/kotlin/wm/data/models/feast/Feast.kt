package wm.data.models.feast

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import wm.data.models.city.City
import wm.data.models.town.Town

class Feast(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Feast>(Feasts)
    var name by Feasts.name
    var dates by Feasts.dates
    var likes by Feasts.likes
    var town by Town referencedOn Feasts.townId
    var city by City referencedOn Feasts.cityId
    var image by Feasts.image
    var description by Feasts.description
}