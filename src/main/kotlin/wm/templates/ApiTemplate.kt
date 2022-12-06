package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*

class ApiTemplate: Template<FlowContent> {

    override fun FlowContent.apply() {
        div("mainbox") {
            h2 {
                +"Consigue datos en json"
            }
            p { +"Esto es una mini introduccion para que te pongas a leer un rato, no hay secreto aqui el codigo es libre siempre y cuando pagues pero es gratis si no es dinero lo que pagas."}
            p { +"GET /api/fiestaspatronales/all" }
            p { +"GET /api/fiestaspatronales/feasts" }
            p { +"GET /api/fiestaspatronales/citys" }
            p { +"GET /api/fiestaspatronales/towns" }
            p { +"GET /api/fiestaspatronales/{id}" }
            p { +"GET /api/fiestaspatronales/{id}/likes" }
            p { +"GET /api/fiestaspatronales/{id}/comments" }
            p { +"GET /api/fiestaspatronales/{id}/images" }
            p { +"PUT /api/fiestaspatronales/{id}/like" }
            p { +"PUT /api/fiestaspatronales/{id}/unlike" }
            p { +"POST /api/fiestaspatronales/{id}/comments" }
        }
    }
}