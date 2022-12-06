package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.DAOInstances

class DetailTemplate(val dao: DAOInstances, tableId: String) : Template<FlowContent> {
    private val feastDAO = dao.feastDAO
    private val feastId = tableId

    override fun FlowContent.apply() {

        transaction {
            val feast = feastDAO.getFeastById(feastId.toInt())
            div("mainbox") {
                if (feast != null) {
                    div("detailbox") {
                        h2 {
                            +feast.name
                        }
                        img {
                            src = "/static"
                            alt = "foto-fiesta"
                        }
                        p {
                            +"Fecha"; br
                            b { +feast.dates }
                        }
                        p {
                            +"Ciudad"; br
                            b { +feast.city.name }}
                        p {
                            +"Pueblo"; br
                            b { +feast.town.name }}
                        p {
                            val desc = feast.description ?: "Sin descripcion"
                            +desc
                        }
                        iframe {
                            src = dao.urlMaps+feast.town.location
                            width = "700"
                            height = "450"
                            style = "border:0;"
                        }
                    }
                } else {
                    div {
                        h1 {
                            +"Not found"
                        }
                    }
                }
            }
        }
    }
}