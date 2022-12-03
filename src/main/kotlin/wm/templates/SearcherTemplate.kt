package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

data class SearcherTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {

    override fun FlowContent.apply() {
        h1 {
            +"Buscador de fiestas patronales"
        }
        div {
            searchInput {
                name = "search"
                placeholder = "Busca una fiesta"
                onChange = "${feastDAO.searchFeast(name)}"
            }

        }
    }
}