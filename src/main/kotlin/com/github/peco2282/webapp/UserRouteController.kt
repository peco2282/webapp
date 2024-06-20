package com.github.peco2282.webapp

import com.github.peco2282.webapp.entity.User
import com.github.peco2282.webapp.form.UserForm
import com.github.peco2282.webapp.mapper.UserMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


/**
 * All xxMapping is /user/xx
 *
 * @author peco2282
 */
@Controller
@RequestMapping("/users")
class UserRouteController(private val mapper: UserMapper) : ILoggable {

  /**
   * Create new user(GET)
   *
   * @see newUser
   */
  @GetMapping("/create")
  fun create(model: Model): String {
    return "users/new"
  }

  /**
   * Create new user(POST)
   *
   * @see create
   */
  @PostMapping("/create")
  fun newUser(@Validated form: UserForm, model: Model): String {
    val user = form.convert(mapper.selectByName(form.name) ?: User())
    logger().trace(user)
    mapper.addUser(user)
    model.addAttribute("user", user)
    return "users/confirm"
  }

  /**
   * Update user data.
   * if user not found, goto 404
   */
  @GetMapping("/update/{id}")
  fun update(@PathVariable id: String, model: Model): String {
    val user = mapper.selectById(id)
    if (user == null) {
      model.addAttribute(ERROR, "USER NOT FOUND")
      return "error/404"
    }
    logger().debug(user)
    model.addAttribute("user", user)
    return "users/update"
  }

  /**
   * Show user profile by name.
   */
  @GetMapping("/show/{name}")
  fun show(model: Model, @PathVariable name: String): String {
    val user = mapper.selectByName(name)
    println(user)
    model.addAttribute("user", user)
    return "users/profile"
  }

  @GetMapping("/{name}/token")
  fun createToken(@PathVariable("name") name: String): String {
    val user = mapper.selectByName(name.strip())
    println(user)
    return "error/404"
  }
}