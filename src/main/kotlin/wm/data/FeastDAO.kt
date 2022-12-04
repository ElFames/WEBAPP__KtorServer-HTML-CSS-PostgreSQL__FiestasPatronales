package wm.data

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.*

class FeastDAO {

    fun addCity(name: String) {
        transaction {
            City.new {
                this.name = name
            }
        }
    }

    fun addTown(name: String, city: String) {
        transaction {
            Town.new {
                this.name = name
                this.city = city
            }
        }
    }

    fun searchFeast(name: String): List<Feast>? {
        var feasts: List<Feast>? = null
        val regex = Regex(".*$name.*")
        transaction {
            feasts = Feast.find { Feasts.name like regex.pattern }.toList()
        }
        return feasts
    }

    fun addFeast(feastDataInList: MutableList<String>) {
        transaction {
            Feast.new {
                name = feastDataInList[0]
                dates = feastDataInList[1]
                city = feastDataInList[2]
                town = feastDataInList[3]
                description = feastDataInList[4]
                image = feastDataInList[5]
            }
        }
    }

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
}