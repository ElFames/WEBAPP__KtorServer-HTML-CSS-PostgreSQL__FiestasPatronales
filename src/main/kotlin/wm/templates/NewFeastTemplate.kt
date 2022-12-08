package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import wm.data.DAOInstances

class NewFeastTemplate(dao: DAOInstances) : Template<FlowContent> {
    private val cityDAO = dao.cityDAO
    private val townDAO = dao.townDAO

    override fun FlowContent.apply() {
        div("mainbox") {
            h2 {
                +"Sube tu fiesta y ayuda a que otros la encuentren"
            }
            div {
                postForm {
                    action = "/fiestaspatronales/addFeast"
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
                        transaction {
                            cityDAO.getAllCitys().forEachIndexed { i, city ->
                                option {
                                    id = "option$i"
                                    value = city.name
                                    text(city.name)
                                }
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

                        transaction {
                            townDAO.getAllTowns().forEachIndexed { i, town ->
                                option {
                                    id = "option$i"
                                    value = town.name
                                    text(town.name)
                                }
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
}