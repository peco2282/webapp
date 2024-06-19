package com.github.peco2282.webapp.authentication

import com.github.peco2282.webapp.ILoggable
import com.github.peco2282.webapp.trace
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken


@Configuration
@EnableWebSecurity
class WebAuthenticationProvider : AuthenticationProvider, ILoggable {
  /**
   * Performs authentication with the same contract as
   * [org.springframework.security.authentication.AuthenticationManager.authenticate]
   * .
   * @param authentication the authentication request object.
   * @return a fully authenticated object including credentials. May return
   * `null` if the `AuthenticationProvider` is unable to support
   * authentication of the passed `Authentication` object. In such a case,
   * the next `AuthenticationProvider` that supports the presented
   * `Authentication` class will be tried.
   * @throws AuthenticationException if authentication fails.
   */
  override fun authenticate(authentication: Authentication): Authentication {
    logger().trace(authentication)
    return BearerTokenAuthenticationToken("")
  }

  /**
   * Returns `true` if this <Code>AuthenticationProvider</Code> supports the
   * indicated <Code>Authentication</Code> object.
   *
   *
   * Returning `true` does not guarantee an
   * `AuthenticationProvider` will be able to authenticate the presented
   * instance of the `Authentication` class. It simply indicates it can
   * support closer evaluation of it. An `AuthenticationProvider` can still
   * return `null` from the [.authenticate] method to
   * indicate another `AuthenticationProvider` should be tried.
   *
   *
   *
   * Selection of an `AuthenticationProvider` capable of performing
   * authentication is conducted at runtime the `ProviderManager`.
   *
   * @param authentication
   * @return `true` if the implementation can more closely evaluate the
   * `Authentication` class presented
   */
  override fun supports(authentication: Class<*>): Boolean {
    return javaClass.isAssignableFrom(authentication)
  }
}