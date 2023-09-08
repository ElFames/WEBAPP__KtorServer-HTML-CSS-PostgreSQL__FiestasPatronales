package wm.data.dao

import io.ktor.server.auth.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.models.user.User
import wm.data.models.user.Users
class UserDAO {
    val usersForApi = getAllUsers()
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
        if (!checkUser(newNickname, newPassword)){
            transaction {
                User.new {
                    nickname = newNickname
                    password = newPassword
                }
            }
        }
    }
    private fun getAllUsers(): MutableList<UserPasswordCredential> {
        val resultList = mutableListOf<UserPasswordCredential>()
        transaction {
            User.all().forEach {
                resultList.add(UserPasswordCredential(it.nickname,it.password))
            }
        }
        return resultList
    }
    fun deleteUser(nickname: String, password: String) {
        transaction {
            User.find { Users.nickname eq nickname and (Users.password eq password) }.firstOrNull()?.delete()
        }
    }
}