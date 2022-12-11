package wm

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import wm.data.DAOInstances
import wm.data.DataBase
import wm.plugins.*

fun main() {
    DataBase.init()
    startApiServer()
}
fun Application.module() {
    val dao = DAOInstances()
    configureTemplating()
    configureSerialization(dao)
    configureRouting(dao)
}
fun startApiServer() =
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)