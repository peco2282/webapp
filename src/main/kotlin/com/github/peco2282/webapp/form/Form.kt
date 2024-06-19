package com.github.peco2282.webapp.form

fun interface Form<T> {
  //  fun convert(): T
  fun convert(instance: T): T
}