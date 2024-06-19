package com.github.peco2282.webapp

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebAppConfig : WebMvcConfigurer {
  override fun addViewControllers(registry: ViewControllerRegistry) {
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/hello").setViewName("hello");
    registry.addViewController("/login").setViewName("login");
//    registry.addViewController("/survey").setViewName("survey/survey")
//    registry.addViewController("/vote").setViewName("vote/vote")
  }
}