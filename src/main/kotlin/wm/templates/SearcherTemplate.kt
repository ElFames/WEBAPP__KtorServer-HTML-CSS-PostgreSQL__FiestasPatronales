package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO
import wm.models.Feast

data class SearcherTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {
    private var searchResult: List<Feast>? = null
    override fun FlowContent.apply() {
        div("mainbox") {
            table {
                tr {
                    th {
                        +"Encuentra tu fiesta"
                    }
                }
                tr {
                    td {
                        searchInput {
                            id = "searcher"
                            name = "search"
                            placeholder = "Busca una fiesta"
                            onChange = "${searchHandler(name)}"
                        }
                    }
                }
            }
            this.insert(SearchResultTemplate(searchResult), TemplatePlaceholder())
        }
    }
    private fun searchHandler(name: String) {
        searchResult = feastDAO.searchFeast(name)
    }
}