package wm.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Citys: IntIdTable() {
    val name = varchar("name",50).uniqueIndex()
    val location = varchar("location",500).nullable()
}