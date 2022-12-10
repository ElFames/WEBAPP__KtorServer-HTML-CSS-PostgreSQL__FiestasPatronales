package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.DAOInstances
import wm.models.Feast

data class SearcherTemplate(val dao: DAOInstances, val search: String?) : Template<FlowContent> {
    private val feastDAO = dao.feastDAO
    private var searchResult: MutableList<Feast> = mutableListOf()
    override fun FlowContent.apply() {

        div("mainbox") {
            h2 {
                +"Encuentra tu fiesta"
            }
            postForm {
                action = "/findSearch"
                encType = FormEncType.textPlain

                searchInput {
                    id = "searcher"
                    name = "search"
                    value = search?:""
                    placeholder = "Busca una fiesta"
                    onChange = "${searchHandler(value)}"
                }
                submitInput(classes="button") {
                    value = "Buscar"
                    attributes["aria-selected"] = "true"
                }
            }
            this.insert(SearchResultTemplate(searchResult), TemplatePlaceholder())
        }
    }
    private fun searchHandler(name: String) {
        searchResult.removeAll { true }
        searchResult = feastDAO.searchFeast(name)
    }
}