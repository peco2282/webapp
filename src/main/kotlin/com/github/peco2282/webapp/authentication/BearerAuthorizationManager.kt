package com.github.peco2282.webapp.authentication

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import java.util.function.Supplier

class BearerAuthorizationManager:  AuthorizationManager<RequestAuthorizationContext> {
  /**
   * Determines if access is granted for a specific authentication and object.
   * @param authentication the [Supplier] of the [Authentication] to check
   * @param object the [T] object to check
   * @return an [AuthorizationDecision] or null if no decision could be made
   */
  override fun check(
    authentication: Supplier<Authentication>,
    context: RequestAuthorizationContext
  ) = AuthorizationDecision(context.request.requestURI.contains("/api/"))
}