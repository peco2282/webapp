package com.github.peco2282.webapp

import com.github.peco2282.webapp.authentication.AuthConstant
import com.github.peco2282.webapp.entity.Role
import com.github.peco2282.webapp.entity.User
import com.github.peco2282.webapp.mapper.UserMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@Sql(scripts = ["/com/github/peco2282/webapp/database/schema.sql", "/com/github/peco2282/webapp/database/data.sql"])
class WebappApplicationTests(@Autowired wac: WebApplicationContext) {
  //  @Autowired
//  lateinit var wac: WebApplicationContext //= MockMvc()
  var mvc: MockMvc = webAppContextSetup(wac)
    .build()

  @Autowired
  lateinit var mapper: UserMapper

  @Test
  fun testAuthorization() {
    mvc.perform(
      get("/api/users/all")
        .accept(MediaType.APPLICATION_JSON)
        .header(
          AuthConstant.AUTHORIZATION,
          "${AuthConstant.BEARER_SPACE}eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTYyMDUzNTYsImlhdCI6MTcxNjIwMTc1Niwic3ViIjoicGVjbzIyODIifQ.oyVT7fcTrN7Hgt0fl8CglKxuC46ra4rMjmBMH5_obTo"
        )
    )
      .andExpect(status().`is`(200))
      .andExpect(jsonPath("$").isArray)

    mvc.perform(
      post("/api/votes/add")
        .param("title", "title1")
        .param("description", "description1")
        .param("choices", "choice1,choice2,choice3")
    ).andExpect(status().`is`(204))
  }

  @Test
  fun testController() {
    mvc.perform(
      get("/users/create")
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().`is`(200))
  }


  @Test
  fun contextLoads() {
    val user = mapper.selectByRoleId(1)
    if (user.isNotEmpty()) {
      println(user)
      assertArrayEquals(user[0].roles(), Role.entries.toTypedArray())
    }
  }

  @Test
  fun addUser() {
    println("Before")
    val before = mapper.selectByRoleId(2)
    println(before)
    mapper.addUser(User.from(0, "user1", "pass", 2, 0))
    println("After")
    val after = mapper.selectByRoleId(2)
    println(after)
    assertThat(before)
  }

  fun update() {
    val user = mapper.selectByName("peco2282")
    if (user != null) {
      user.role += 1
      mapper.updateUser("peco2282", user)
    }
  }

  fun del() {}
}
