package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

data class HomeTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div("mainbox") {
            table {
                tr {
                    th {
                        +"Fiestas Populares de España"
                    }
                }
                tr {
                    td {
                        +"Esto es un resumen del significado de las fiestas patronales que las hay en todas las comunidades practicamente, si te pones a buscar algo encontraras pero bueno tampoco hay gran cosa"
                        br
                        br
                        +"El diseño es una mierda y esas fotos de ahi arriba me estan poniendo nervioso, luego hay diseños por ahí con una pinta que te cagas y son un desastre"
                    }
                }
            }
        }
    }
}