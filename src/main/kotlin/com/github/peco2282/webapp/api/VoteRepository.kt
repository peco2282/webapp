package com.github.peco2282.webapp.api

import com.github.peco2282.webapp.entity.Vote
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VoteRepository: CrudRepository<Vote, Int>
//interface SurveyRepository: CrudRepository<Vote, Int>