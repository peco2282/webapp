package com.github.peco2282.webapp.mapper

import com.github.peco2282.webapp.entity.Survey
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface SurveyMapper : SqlMapper<Survey>{
  @Select("SELECT * FROM survey")
  override fun selectAll(): List<Survey>

  @Select("SELECT * FROM web_app.survey")
  override fun selectById(id: String): Survey
}