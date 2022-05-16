package poc.com.br

import io.ktor.server.application.Application
import poc.com.br.plugins.configureAuthorization
import poc.com.br.plugins.configureExceptionHandler
import poc.com.br.plugins.configureRouting
import poc.com.br.plugins.configureSerialization

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureAuthorization(environment = environment)
    configureRouting()
    configureExceptionHandler()
    configureSerialization()
}
