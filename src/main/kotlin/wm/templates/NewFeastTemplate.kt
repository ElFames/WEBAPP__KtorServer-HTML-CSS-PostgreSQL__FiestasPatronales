package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.FeastDAO

class NewFeastTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {

    override fun FlowContent.apply() {
        div("mainbox") {
            table {
                tr {
                    th {
                        +"Sube tu fiesta y ayuda a que otros la encuentren"
                    }
                }
                tr {
                    td {
                        postForm {
                            action = "/fiestaspatronales/addFeast"
                            encType = FormEncType.multipartFormData

                            input {
                                name = "name"
                                type = InputType.text
                                placeholder = "Nombre de la fiesta"
                                required = true
                            }
                            br {}

                            input {
                                name = "dates"
                                type = InputType.text
                                placeholder = "dd/mm - dd/mm"
                                required = true
                            }
                            br {}

                            label {
                                id = "city"
                                htmlFor = "city"
                                +"Select City"
                            }
                            select {
                                id = "city"
                                required = true
                                transaction {
                                    feastDAO.getAllCitys().forEachIndexed { i, city ->
                                        option {
                                            id = "option$i"
                                            value = city.name
                                            text(city.name)
                                        }
                                    }
                                }
                            }
                            br {}

                            input {
                                name = "town"
                                type = InputType.text
                                placeholder = "Pueblo"
                                required = true
                            }
                            br {}

                            input {
                                name = "description"
                                type = InputType.text
                                placeholder = "Descripcion"
                            }
                            br {}

                            label {
                                id = "image-button"
                                htmlFor = "image"
                                +"Subir Imagen"
                            }
                            br {}

                            input {
                                name = "image"
                                type = InputType.file
                                placeholder = "Explorar"
                                required = true
                            }
                            br {}

                            input(classes = "button"){
                                type = InputType.submit
                                value = "APORTAR"
                                attributes["aria-selected"] = "true"
                            }
                        }
                    }
                }
            }
        }
    }
}