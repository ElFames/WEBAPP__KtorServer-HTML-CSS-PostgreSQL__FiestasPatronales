package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.DAOInstances
import wm.models.Feast

data class SearcherTemplate(val dao: DAOInstances) : Template<FlowContent> {
    private val feastDAO = dao.feastDAO
    private var searchResult: MutableList<Feast> = mutableListOf()
    override fun FlowContent.apply() {

        div("mainbox") {
            h2 {
                +"Encuentra tu fiesta"
            }
            searchInput {
                id = "searcher"
                name = "search"
                value = ""
                placeholder = "Busca una fiesta"
                onChange = "${searchHandler(value)}"
                onSubmit = "${searchHandler(value)}"
            }
            this.insert(SearchResultTemplate(searchResult), TemplatePlaceholder())
        }
    }
    private fun searchHandler(name: String) {
        searchResult.removeAll { true }
        searchResult = feastDAO.searchFeast(name)
    }
}