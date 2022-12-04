package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

class ApiTemplate: Template<FlowContent> {

    override fun FlowContent.apply() {
        div("mainbox") {
            table {
                tr {
                    th {
                        +"Consigue datos en json para desarrollar tu aplicacion"
                    }
                }
                tr {
                    td {
                        +"Esto es una mini introduccion en modo de parrafon para que te pongas a leer un rato, no hay secreto aqui el codigo es libre siempre y cuando pagues pero es gratis si no es dinero lo que pagas."
                        br
                        br
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
        }
    }
}