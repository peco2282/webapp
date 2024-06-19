package com.github.peco2282.webapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebappApplication {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<WebappApplication>(*args)
    }
  }
}
