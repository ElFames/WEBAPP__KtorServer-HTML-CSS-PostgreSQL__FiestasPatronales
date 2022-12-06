package wm.data

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.User
import wm.models.Users

class UserDAO {
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