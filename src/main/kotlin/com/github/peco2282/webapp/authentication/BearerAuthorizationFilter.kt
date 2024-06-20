package com.github.peco2282.webapp.authentication

import com.auth0.jwt.JWT
import com.github.peco2282.webapp.HeaderException
import com.github.peco2282.webapp.ILoggable
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter

class BearerAuthorizationFilter(manager: AuthenticationManager) : BearerTokenAuthenticationFilter(manager), ILoggable {
  /**
   * Same contract as for `doFilter`, but guaranteed to be
   * just invoked once per request within a single request thread.
   * See [.shouldNotFilterAsyncDispatch] for details.
   *
   * Provides HttpServletRequest and HttpServletResponse arguments instead of the
   * default ServletRequest and ServletResponse ones.
   */
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    val authorization = request.getHeader(AuthConstant.AUTHORIZATION)
    if (request.requestURI.contains("/api/")) {
      if (authorization == null) {
        val exc = HeaderException(AuthConstant.AUTHORIZATION, "/api/**")
        val entry = BearerTokenAuthenticationEntryPoint()
        entry.setRealmName(exc.message)
        entry.commence(request, response, exc)
//        response.status = 401

        println(entry)
        response.sendError(401, exc.message)
        return
//        throw ServletException(exc)
      } else {
//      throw object : AuthenticationException("/api/** endpoints required correct Authorization Header.") {}
        val base = authorization.substring(AuthConstant.BEARER_SPACE.length)

        val subject = JWT.decode(base).subject
        if (subject != "peco2282") throw object : AuthenticationException("content is invalid") {}
        request.authenticate(response)
        filterChain.doFilter(request, response)

      }
    } // else super.doFilterInternal(request, response, filterChain)
    else filterChain.doFilter(request, response)
  }
}