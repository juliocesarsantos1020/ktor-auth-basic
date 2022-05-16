package poc.com.br.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.authentication
import io.ktor.server.auth.basic
import io.ktor.server.config.ApplicationConfig
import poc.com.br.util.AuthenticationException

fun Application.configureAuthorization(environment: ApplicationEnvironment) {
    authentication {
        basic {
            validate { credentials ->
                if (verifyCredentials(config = environment.config, credentials = credentials)) {
                    UserIdPrincipal(credentials.name)
                } else {
                    throw AuthenticationException()
                }
            }
        }
    }
}

fun verifyCredentials(config: ApplicationConfig, credentials: UserPasswordCredential): Boolean =
    credentials.name == config.property("authentication.username")
        .getString() && credentials.password == config.property("authentication.password").getString()
