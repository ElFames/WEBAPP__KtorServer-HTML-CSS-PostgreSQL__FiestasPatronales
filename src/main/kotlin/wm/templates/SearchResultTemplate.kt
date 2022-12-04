package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.models.Feast

class SearchResultTemplate(private val searchResult: List<Feast>?) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div("search-results") {
            id = "results"
            div {
                searchResult?.forEachIndexed { index, feast ->
                    div {
                        id = "result-$index"
                        p { text(feast.name) }
                        onClick = "window.location.href = '/fiestaspatronales/${feast.id}'"
                    }
                } ?: p { +"No se han encontrado resultados, puedes subir tu fiesta en 'APORTAR' para que otros la encuentren" }
            }
        }
    }
}