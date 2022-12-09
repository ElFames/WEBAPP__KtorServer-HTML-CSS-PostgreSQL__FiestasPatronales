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
            p { +"GET /fiestaspatronales/api/all" }
            p { +"GET /fiestaspatronales/api/feasts" }
            p { +"GET /fiestaspatronales/api/citys" }
            p { +"GET /fiestaspatronales/api/towns" }
            p { +"GET /fiestaspatronales/api/{id}" }
            p { +"PUT /fiestaspatronales/api/{id}/description" }
        }
    }
}