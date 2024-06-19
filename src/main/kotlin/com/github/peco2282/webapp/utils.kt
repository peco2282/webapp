package com.github.peco2282.webapp

import org.slf4j.Logger
import org.slf4j.LoggerFactory


inline fun <reified T> T.thisLogger(): Logger = LoggerFactory.getLogger(T::class.java)

const val ERROR = "errormsg"
