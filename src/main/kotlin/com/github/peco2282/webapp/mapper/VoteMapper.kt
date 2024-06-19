package com.github.peco2282.webapp.mapper

import com.github.peco2282.webapp.entity.Vote
import org.apache.ibatis.annotations.*

@Mapper
interface VoteMapper : SqlMapper<Vote> {
  @Select("SELECT * FROM web_app.vote")
  override fun selectAll(): List<Vote>

  @Insert("INSERT INTO web_app.vote(title, description, choices) VALUE (#{title}, #{description}, #{choices})")
  fun addVote(vote: Vote)

  @Select("SELECT * FROM web_app.vote WHERE id = #{id}")
  override fun selectById(id: String): Vote

  @Update
  fun update(vote: Vote): Boolean

  @Delete
  fun delete(): Boolean
}