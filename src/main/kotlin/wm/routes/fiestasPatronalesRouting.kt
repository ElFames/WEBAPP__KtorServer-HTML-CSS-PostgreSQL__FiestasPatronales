package wm.routes

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import wm.data.FeastDAO
import wm.templates.LayoutTemplate

fun Route.fiestasPatronalesRouting() {
    val feastDAO = FeastDAO()
    feastDAO.connect()


    route("/fiestaspatronales") {

        get {
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "home"
            }
        }
        get("searcher") {
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "searcher"
            }
        }
        get("popular") {
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "popular"
            }
        }
        get("nextRoute") {
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "nextRoute"
            }
        }
        get("contact") {
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "contact"
            }
        }
        get("api") {
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