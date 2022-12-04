package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO
import wm.models.Feast

data class SearcherTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {
    var searchResult: List<Feast>? = null
    override fun FlowContent.apply() {
        h1 {
            +"Buscador de fiestas patronales"
        }
        div {
            id = "searcher-container"
            searchInput {
                id = "searcher"
                name = "search"
                placeholder = "Busca una fiesta"
                onChange = "${searchHandler(name)}"
            }
        this.insert(SearchResultTemplate(searchResult), TemplatePlaceholder())
        }
    }
    private fun searchHandler(name: String) {
        searchResult = feastDAO.searchFeast(name)
    }
}