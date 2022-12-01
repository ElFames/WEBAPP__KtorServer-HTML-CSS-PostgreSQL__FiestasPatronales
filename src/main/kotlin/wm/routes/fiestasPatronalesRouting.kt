package wm.routes

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import wm.data.FeastDAO
import wm.templates.LayoutTemplate

fun Route.fiestasPatronalesRouting() {
    val feastDAO = FeastDAO()


    route("/fiestaspatronales") {

        get("home") {
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
    }
}