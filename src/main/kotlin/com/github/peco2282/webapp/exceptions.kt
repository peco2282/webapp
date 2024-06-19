package com.github.peco2282.webapp

import org.slf4j.Logger
import org.springframework.security.core.AuthenticationException

class HeaderException(header: String, vararg endpoints: String) : AuthenticationException("`$header` header is required ${endpointsAt(true, *endpoints)}. But $header is null or ''")
class TokenException(token: String) : AuthenticationException("Token `$token` is invalid")

fun endpointsAt(requireAt: Boolean, vararg endpoints: String): String = if (requireAt) "at " else "" + endpoints.joinToString(", ")

interface ILoggable {
  fun logger(): Logger = thisLogger()
}

fun Logger.trace(obj: Any) = trace(obj.toString())
fun Logger.debug(obj: Any) = debug(obj.toString())
fun Logger.info(obj: Any) = info(obj.toString())
fun Logger.warn(obj: Any) = warn(obj.toString())
fun Logger.error(obj: Any) = error(obj.toString())
