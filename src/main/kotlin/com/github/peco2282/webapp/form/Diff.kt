package com.github.peco2282.webapp.form

fun interface Diff<T> {
  fun difference(before: T, after: T): T
}