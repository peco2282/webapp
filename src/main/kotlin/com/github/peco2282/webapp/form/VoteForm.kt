package com.github.peco2282.webapp.form

import com.github.peco2282.webapp.entity.Vote

class VoteForm: Form<Vote> {
  override fun convert(instance: Vote): Vote = instance
}