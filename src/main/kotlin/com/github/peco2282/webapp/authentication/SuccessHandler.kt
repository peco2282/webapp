package com.github.peco2282.webapp.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.peco2282.webapp.ILoggable
import com.github.peco2282.webapp.trace
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.util.*


class SuccessHandler : AuthenticationSuccessHandler, ILoggable {
  /**
   * Called when a mapper has been successfully authenticated.
   * @param request the request which caused the successful authentication
   * @param response the response
   * @param authentication the <tt>Authentication</tt> object which was created during
   * the authentication process.
   */
  override fun onAuthenticationSuccess(
    request: HttpServletRequest,
    response: HttpServletResponse,
    authentication: Authentication
  ) {
    logger().trace(authentication)
    val now = Date()
    val cal = Calendar.getInstance()
    cal.setTime(now)
    cal.add(Calendar.MINUTE, 60)
    val expTime: Date = cal.time

    val token: String = JWT.create()
      .withSubject(authentication.principal.toString())
      .withIssuedAt(now)
      .withExpiresAt(expTime)
      .sign(Algorithm.HMAC256(AuthConstant.SECRET_KEY.toByteArray()))
//JWT.decode("").signature
    response.status = HttpStatus.OK.value()
    response.addHeader(AuthConstant.AUTHORIZATION, "${AuthConstant.BEARER_SPACE}$token")
  }
}