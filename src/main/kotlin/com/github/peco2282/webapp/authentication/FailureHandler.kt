package com.github.peco2282.webapp.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

class FailureHandler: AuthenticationFailureHandler {
  /**
   * Called when an authentication attempt fails.
   * @param request the request during which the authentication attempt occurred.
   * @param response the response.
   * @param exception the exception which was thrown to reject the authentication
   * request.
   */
  override fun onAuthenticationFailure(
    request: HttpServletRequest,
    response: HttpServletResponse,
    exception: AuthenticationException
  ) {
    println("UnAuthorized ${exception.javaClass.name} " + exception.localizedMessage)
    response.status = HttpStatus.UNAUTHORIZED.value()
  }
}