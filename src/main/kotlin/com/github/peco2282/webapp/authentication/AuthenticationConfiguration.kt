package com.github.peco2282.webapp.authentication

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter

@Configuration
class AuthenticationConfiguration(
  private val provider: WebAuthenticationProvider
) : GlobalAuthenticationConfigurerAdapter() {
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.authenticationProvider(provider)
  }
}