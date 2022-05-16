package poc.com.br.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    routing {
        authenticate {
            get("/test") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.respond(message = Response(message = "Hello ${principal.name}"), status = HttpStatusCode.OK)
            }
        }
    }
}

@Serializable
data class Response(val message: String)
