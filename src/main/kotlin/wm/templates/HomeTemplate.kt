package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.DAOInstances

data class HomeTemplate(val dao: DAOInstances) : Template<FlowContent> {
    private val feastDAO = dao.feastDAO
    private val cityDAO = dao.cityDAO
    private var currentCity = ""
    override fun FlowContent.apply() {
        div("mainbox") {
            h2 {
                +"Fiestas Populares de España"
            }
            transaction {
                feastDAO.getAllFeasts().groupBy {
                    it.city
                }.forEach {
                    div(classes="feastLine") {
                        p("city") {
                            +it.key.name
                            br;br
                            it.value.forEach {
                                a {
                                    href = "${it.id.value}"
                                    +it.name
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}