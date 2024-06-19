package com.github.peco2282.webapp

import com.github.peco2282.webapp.mapper.VoteMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/votes")
class VoteRouteController(private val mapper: VoteMapper) {
  @GetMapping("/show/{id}")
  fun showId(model: Model, @PathVariable id: String): String {
    val vote = mapper.selectById(id)
    println(vote)
    model.addAttribute("vote", vote)
    return "vote/show"
  }

//  @GetMapping("/show/")
}