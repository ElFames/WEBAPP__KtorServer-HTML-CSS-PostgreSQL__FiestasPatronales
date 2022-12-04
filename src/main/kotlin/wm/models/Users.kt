package wm.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val nickname = varchar("name",50).uniqueIndex()
    val password = varchar("password",50)
}