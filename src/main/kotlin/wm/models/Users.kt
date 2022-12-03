package wm.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val nickname = varchar("nickname", 50)
    val password = varchar("password", 50)
}