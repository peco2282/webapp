package com.github.peco2282.webapp.api

import com.github.peco2282.webapp.entity.Survey
import com.github.peco2282.webapp.entity.User
import com.github.peco2282.webapp.entity.Vote
import com.github.peco2282.webapp.mapper.SurveyMapper
import com.github.peco2282.webapp.mapper.UserMapper
import com.github.peco2282.webapp.mapper.VoteMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api", produces = [MediaType.APPLICATION_JSON_VALUE])
class RestCotroller(var userMapper: UserMapper, var voteMapper: VoteMapper, var surveyMapper: SurveyMapper) {
//  @Autowired
//  lateinit var surveyRepository: SurveyRepositorypy

  @GetMapping("/users/all")
  fun allUsers(
//    @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String
  ): ResponseEntity<List<User>> {
    val users = userMapper.selectAll()
//    println("Auth: $authorization")
    println("Users: $users")
    users.forEach { it.password = "******(PASSWORD)" }
    val header = HttpHeaders()
    header.contentType = MediaType.APPLICATION_JSON
    return ResponseEntity(users, header, HttpStatus.OK) // users
  }

  @GetMapping("/votes/all")
  fun allVotes(): ResponseEntity<List<Vote>> {
    val all = voteMapper.selectAll()
    val header = HttpHeaders()
    header.contentType = MediaType.APPLICATION_JSON
//    : MutableList<MutableMap<String, Any>> =
    //    voteRepository.findAll().forEach { println(it) }
    return ResponseEntity(all, header, HttpStatus.OK) //template.query("SELECT * FROM web_app.vote;", voteMapper)
  }

  @PostMapping("/votes/add")
  fun addVote(
    @RequestParam title: String, @RequestParam description: String, @RequestParam choices: String
  ): ResponseEntity<Void> {
    val vote = Vote().apply {
      this.title = title
      this.description = description
      this.choices = choices
    }
    voteMapper.addVote(vote)
    println(vote.choicesToString)
    return ResponseEntity(null, HttpStatus.NO_CONTENT)
  }

  @GetMapping("/survey/all")
  fun allSurveies(): List<Survey> = surveyMapper.selectAll() //template.queryForList("SELECT * FROM web_app.survey")
}