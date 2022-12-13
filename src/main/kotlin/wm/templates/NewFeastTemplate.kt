package wm.templates

import kotlinx.html.*
import org.jetbrains.exposed.sql.SizedIterable
import wm.models.city.City
import wm.models.town.Town

fun FlowContent.newFeastTemplate(towns: SizedIterable<Town>, citys: SizedIterable<City>) {
    div("mainbox") {
        h2 {
            +"Sube tu fiesta y ayuda a que otros la encuentren"
        }
        div {
            postForm {
                action = "/addFeast"
                encType = FormEncType.multipartFormData
                input {
                    name = "name"
                    type = InputType.text
                    placeholder = "Nombre de la fiesta"
                    required = true
                }
                br
                input {
                    name = "dates"
                    type = InputType.text
                    placeholder = "dd/mm - dd/mm"
                    required = true
                }
                br
                label {
                    id = "city"
                    htmlFor = "city"
                    +"Select City"
                }
                br
                select {
                    name = "city"
                    required = true
                    citys.forEachIndexed { i, city ->
                        option {
                            id = "option$i"
                            value = city.name
                            text(city.name)
                        }
                    }
                }
                br
                label {
                    id = "town"
                    htmlFor = "town"
                    +"Select Town"
                }
                br
                select {
                    name = "town"
                    required = true

                    towns.forEachIndexed { i, town ->
                        option {
                            id = "option$i"
                            value = town.name
                            text(town.name)
                        }
                    }
                }
                br
                input(classes="comment") {
                    name = "description"
                    type = InputType.text
                    placeholder = "Descripcion"
                }
                br
                label {
                    id = "image-button"
                    htmlFor = "image"
                    +"Subir Imagen"
                }
                br
                input {
                    name = "image"
                    type = InputType.file
                    required = true
                }
                br
                input(classes = "button"){
                    type = InputType.submit
                    value = "APORTAR"
                    attributes["aria-selected"] = "true"
                }
            }
        }
    }
}