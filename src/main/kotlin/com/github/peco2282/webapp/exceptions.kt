package com.github.peco2282.webapp

import org.springframework.security.core.AuthenticationException

class HeaderException(header: String, vararg endpoints: String) :
  AuthenticationException("`$header` header is required ${endpointsAt(true, *endpoints)}. But $header is null or ''")

class TokenException(token: String) : AuthenticationException("Token `$token` is invalid")

fun endpointsAt(requireAt: Boolean, vararg endpoints: String): String =
  if (requireAt) "at " else "" + endpoints.joinToString(", ")

