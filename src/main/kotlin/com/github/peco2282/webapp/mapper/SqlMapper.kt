package com.github.peco2282.webapp.mapper

interface SqlMapper<T> {
  fun selectAll(): List<T>
  fun selectById(id: String): T?
}