package com.github.peco2282.webapp.form

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.peco2282.webapp.entity.Role
import com.github.peco2282.webapp.entity.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.*

class UserForm(
  @NotNull
  @NotBlank
  @NotEmpty
  @Size(min = 1, max = 10)
  val name: String,
  @NotNull
  @NotBlank
  @NotEmpty
  @Size(min = 1, max = 10)
  val password: String
) : Form<User> {
  override fun convert(instance: User): User = instance.apply {
    this.name = this@UserForm.name
    this.password = this@UserForm.password
    this.role = Role.DEFAULT_ROLE
    val secretKey = "secretKey"
    val now = Date()
    val cal = Calendar.getInstance()
    cal.setTime(now)
    cal.add(Calendar.MINUTE, 60)
    val expTime: Date = cal.time

    val token: String = JWT.create()
      .withSubject(password)
      .withIssuedAt(now)
      .withExpiresAt(expTime)
      .sign(Algorithm.HMAC256(secretKey.toByteArray()))
    println(token)
  }

  override fun toString(): String = "UserForm[name=${name}, password=${password}]"
}