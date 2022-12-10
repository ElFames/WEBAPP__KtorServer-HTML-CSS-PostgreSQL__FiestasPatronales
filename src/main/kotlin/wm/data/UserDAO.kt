package wm.data

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.User
import wm.models.Users
class UserDAO {
    val usersForApi = mutableListOf(UserPasswordCredential("admin","admin"))
    fun checkUser(nickname: String?, password: String?): Boolean {
        return transaction {
            User.find { Users.nickname eq "$nickname" and (Users.password eq "$password") }.firstOrNull()
        } != null
    }
    fun getUser(nickname: String?, password: String?): User? {
        return transaction {
            User.find { Users.nickname eq "$nickname" and (Users.password eq "$password") }.firstOrNull()
        }
    }
    fun newUser(newNickname: String, newPassword: String) {
        transaction {
            User.new {
                nickname = newNickname
                password = newPassword
            }
        }
    }
    fun getAllUsers() {
        transaction {
            User.all()
        }
    }
    fun deleteUser(nickname: String, password: String) {
        transaction {
            User.find { Users.nickname eq nickname and (Users.password eq password) }.firstOrNull()?.delete()
        }
    }
}