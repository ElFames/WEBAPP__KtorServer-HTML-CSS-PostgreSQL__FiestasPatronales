package wm

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import wm.data.database.DataManager
import wm.data.database.DataBase
import wm.core.plugins.*

fun main() {
    DataBase.init()
    startApiServer()
}
fun startApiServer() =
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module)
        .start(wait = true)

fun Application.module() {
    val dao = DataManager
    configureTemplating()
    configureSerialization(dao)
    configureRouting(dao)
}