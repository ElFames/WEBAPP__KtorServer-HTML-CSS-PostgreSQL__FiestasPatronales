package wm.templates

import io.ktor.server.html.*
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.p
import wm.data.FeastDAO

data class ApiTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {

    override fun FlowContent.apply() {
        h1 {
            +"Api endpoints"
        }
        div {
            p { +"GET /api/fiestaspatronales" }
            p { +"GET /api/fiestaspatronales/{id}" }
            p { +"GET /api/fiestaspatronales/{id}/likes" }
            p { +"GET /api/fiestaspatronales/{id}/comments" }
            p { +"GET /api/fiestaspatronales/{id}/images" }
            p { +"POST /api/fiestaspatronales/{id}/likes" }
            p { +"POST /api/fiestaspatronales/{id}/comments" }
            p { +"POST /api/fiestaspatronales/add_feast" }
            p { +"POST /api/fiestaspatronales/add_city" }
            p { +"POST /api/fiestaspatronales/add_town" }
            p { +"POST /api/fiestaspatronales/add_user" }
        }
    }
}