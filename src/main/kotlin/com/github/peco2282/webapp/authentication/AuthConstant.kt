package com.github.peco2282.webapp.authentication

import org.springframework.security.crypto.factory.PasswordEncoderFactories

object AuthConstant {
  const val AUTHORIZATION = "Authorization"
  const val SECRET_KEY = "PECO2282"
  const val BEARER = "Bearer"
  const val BEARER_SPACE = "$BEARER "

  val PASSWORD_ENCODER: (CharSequence) -> String = PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode
}