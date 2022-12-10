package wm.models.town

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import wm.models.city.City

class Town(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Town>(Towns)
    var name by Towns.name
    var location by Towns.location
    var city by City referencedOn Towns.cityId
}