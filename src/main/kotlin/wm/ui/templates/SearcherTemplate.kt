package wm.ui.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.models.feast.Feast

fun FlowContent.searcherTemplate(searchResult: MutableList<Feast>) {
    div("mainbox") {
        h2 {
            +"Encuentra tu fiesta"
        }
        getForm {
            action = "/searcher"
            encType = FormEncType.textPlain

            searchInput {
                id = "searcher"
                name = "search"
                placeholder = "Busca una fiesta"
            }
            submitInput(classes="button") {
                value = "Buscar"
                attributes["aria-selected"] = "true"
            }
        }
        insert(SearchResultTemplate(searchResult), TemplatePlaceholder())
    }
}