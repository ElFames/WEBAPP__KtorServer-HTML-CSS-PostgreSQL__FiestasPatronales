package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.DAOInstances

data class HomeTemplate(val dao: DAOInstances) : Template<FlowContent> {
    private val feastDAO = dao.feastDAO

    override fun FlowContent.apply() {
        div("mainbox") {
            h2 {
                +"Fiestas Populares de España"
            }
            p { +"Bienvenidos a la pagina principal de fiestas patronales, este portal ha sido creado para fomentar el viaje por la peninsula haciendo referencia a nuestras tradiciones." }
            p { +"Aqui tienes una lista de todas las fiestas que tenemos registradas agrupadas por ciudades. Si no encuentras tu fiesta aqui, puedes utilizar el buscador que disponemos." }
            p { +"Si no obtienes resultados, puedes añadirla en el apartado 'Aportar' para que otras personas puedan encontrarla la proxima vez." }
            h3 { +"Todas las fiestas agrupadas por ciudades:" }
            transaction {
                feastDAO.getAllFeasts().groupBy {
                    it.city
                }.forEach {
                    div(classes="feastLine") {
                        p {
                            +it.key.name
                            br;br
                            it.value.forEach {
                                a(classes="feast") {
                                    href = "${it.id.value}"
                                    +it.name
                                    br
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}