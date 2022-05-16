package poc.com.br.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.Serializable
import poc.com.br.util.AuthenticationException
import poc.com.br.util.AuthorizationException

@Serializable
data class ErrorMessage(
    val code: Int,
    val message: String
)

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(
                status = HttpStatusCode.Unauthorized,
                message = ErrorMessage(code = HttpStatusCode.Unauthorized.value, message = "Access Denied")
            )
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(
                status = HttpStatusCode.Forbidden,
                message = ErrorMessage(code = HttpStatusCode.Forbidden.value, message = "User is not allowed")
            )
        }
    }
}
