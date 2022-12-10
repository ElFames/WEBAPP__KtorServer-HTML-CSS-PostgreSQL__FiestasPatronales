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
            p { +"GET /api/all" }
            p { +"GET /api/feasts" }
            p { +"GET /api/citys" }
            p { +"GET /api/towns" }
            p { +"GET /api/{id}" }
            p { +"PUT /api/{id}/description" }
            p { +"GET Para iniciar sesion ves al directorio raiz '/'. Para recibir unas credenciales dejanos un comentario y te llegará un correo con las claves. Gracias." }
        }
    }
}