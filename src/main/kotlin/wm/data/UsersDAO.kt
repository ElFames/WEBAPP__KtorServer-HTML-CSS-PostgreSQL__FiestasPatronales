package wm.data

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.User
import wm.models.Users

class UsersDAO {
    fun checkUser(nickname: String?, password: String?): Boolean {
        var checkOk = false
        transaction {
            val userList = User.all()
            userList.forEach {
                if (it.nickname == nickname && it.password == password) {
                    checkOk = true
                    return@forEach
                }
            }
        }
        return checkOk
    }
    fun getUser(nickname: String?, password: String?): User? {
        var user: User? = null
        transaction {
            user = User.find { Users.nickname eq "$nickname" and (Users.password eq "$password") }.firstOrNull()
        }
        return user
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
            User.all().forEach {
                println(it.nickname+it.password)
            }
        }
    }
    fun deleteUser(nickname: String, password: String) {
        transaction {
            User.find { Users.nickname eq nickname and (Users.password eq password) }.firstOrNull()?.delete()
        }
    }
}