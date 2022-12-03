package wm

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import wm.plugins.configureRouting
import wm.plugins.configureSerialization
import wm.plugins.configureTemplating

fun main() {
    //DataBase.init()
    startWebApi()
}
fun Application.module() {
    configureTemplating()
    configureSerialization()
    configureRouting()
}
fun startWebApi() = embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)