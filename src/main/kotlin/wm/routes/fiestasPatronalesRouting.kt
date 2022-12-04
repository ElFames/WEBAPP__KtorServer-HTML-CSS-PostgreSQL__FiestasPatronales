package wm.routes

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import wm.data.FeastDAO
import wm.functions.multipartDataToImage
import wm.models.User
import wm.templates.*

fun Route.fiestasPatronalesRouting() {
    val feastDAO = FeastDAO()
    var currentUser: User? = feastDAO.getUser("miguel","miguel")

    route("/fiestaspatronales") {

        get("/") {
            call.respondRedirect("home")
        }
        get("login") {
            call.respondHtmlTemplate(LoginTemplate()) {}
        }

        post("checkLogin") {
            val textForm = call.receiveText()
            val params = getParamsList(textForm)
            val nickname = params[0]
            val password = params[1]

            if (feastDAO.checkUser(nickname, password)) {
                currentUser = feastDAO.getUser(nickname, password)
                call.respondRedirect("home")
            } else {
                call.respondHtml {
                    body {
                        h1 { +"ERROR 300 ---> No existe el usuario: $nickname..." }
                    }
                }
            }
        }

        post("newUser") {
            val textForm = call.receiveText()
            val params = getParamsList(textForm)
            val nickname = params[0]
            val password = params[1]

            if (feastDAO.checkUser(nickname, password)) {
                call.respondHtml {
                    body {
                        h1 { + "El usuario: $nickname ya esta registrado..." }
                    }
                }
            } else {
                feastDAO.newUser(nickname, password)
                currentUser = feastDAO.getUser(nickname, password)
                call.respondRedirect("home")
            }
        }

        post("addFeast") {
            val feastDataInList = multipartDataToImage(call.receiveMultipart())
            feastDAO.addFeast(feastDataInList)
            call.respondRedirect("home")
        }

        get("home") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "home"
            }
        }
        get("searcher") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "searcher"
            }
        }
        get("newFeast") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "newFeast"
            }
        }
        get("popular") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "popular"
            }
        }
        get("nextRoute") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "nextRoute"
            }
        }
        get("contact") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "contact"
            }
        }
        get("api") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "api"
            }
        }
        post("/newFeast") {
            val partData = call.receiveMultipart()
            var name = ""
            var dates = ""
            var city = ""
            var town = ""
            var likes = 0
            var description: String = ""
            var image: ByteArray? = null
            partData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name){
                            "name" -> name = part.value
                            "dates" -> dates = part.value
                            "city" -> city = part.value
                            "town" -> town = part.value
                            "likes" -> likes = part.value.toInt()
                            else -> description = part.value
                        }
                    }
                    is PartData.FileItem -> {
                        image = part.streamProvider().readBytes()
                    }
                    else -> {}
                }
                part.dispose()
            }
            feastDAO.addFeast(name, dates, city, town, likes, image, description)
            call.respondText("Film storage correctly", status = HttpStatusCode.Created)
        }
    }
}

fun getParamsList(textForm: String): MutableList<String> {
    val params = mutableListOf<String>()
    textForm.reader().forEachLine { params.add(it.split('=')[1]) }
    return params
}
