package wm.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Feasts: IntIdTable() {
    val name = varchar("name",50).uniqueIndex()
    val dates = varchar("dates",50)
    val description = varchar("description",3000).nullable()
    val cityId = reference("city", Citys)
    val townId = reference("town", Towns)
    val image = varchar("image",8000)
    val likes = integer("likes").default(0)
}