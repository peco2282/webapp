package com.github.peco2282

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

class FeatureTest {
  @Test
  fun testJWT() {
    val expire = Date()
    expire.hours += 1
    val issue = Date()
    val token = JWT.create()
      .withExpiresAt(expire)
      .withIssuedAt(issue)
      .withSubject("peco2282")
      .sign(Algorithm.HMAC256("SECRET"))
    println(token)
  }

  @Test
  fun testEncode() {
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTYyMDUzNTYsImlhdCI6MTcxNjIwMTc1Niwic3ViIjoicGVjbzIyODIifQ.oyVT7fcTrN7Hgt0fl8CglKxuC46ra4rMjmBMH5_obTo"
    val subject = JWT.decode(token)
    assertThat(subject.subject).isEqualTo("peco2282")
  }
}