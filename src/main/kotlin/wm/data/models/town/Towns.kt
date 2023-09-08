package wm.data.models.town

import org.jetbrains.exposed.dao.id.IntIdTable
import wm.data.models.city.Citys

object Towns: IntIdTable() {
    val name = varchar("name",50).uniqueIndex()
    val location = varchar("location",50).nullable()
    val cityId = reference("city", Citys)
}