package com.github.peco2282.webapp

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier


inline fun <reified T> T.thisLogger(): Logger = LoggerFactory.getLogger(T::class.java)
fun<T> T?.supplier(): Supplier<T?> = Supplier { this }
fun<T> T?.nonnullSupplier(): Supplier<T> = Supplier { Objects.requireNonNull(this)!! }

const val ERROR = "errormsg"

class Holder<T>(obj: T?) {
  private var holder: T?
  init {
    holder = obj
  }
  fun get(): T = Objects.requireNonNull(holder)!!
  fun set(obj: T) {
    holder = obj
  }

  fun ifAbsent(supplier: Supplier<T?>) {
    val v = supplier.get()
    if (v != null) {holder = v}
  }

  fun ifPresent(consumer: Consumer<T>) = if (holder != null) consumer.accept(holder!!) else {}
  fun remove() {
    holder = null
  }
  companion object {
    private val EMPTY = Holder(null)
    @Suppress("UNCHECKED_CAST")
    fun <T> empty(): Holder<T> = EMPTY as Holder<T>
    fun <T> of(t: T) = Holder(Objects.requireNonNull(t))
    fun <T> ofNullable(obj: T?) = Holder(obj)
  }
}
interface ILoggable {
  fun logger(): Logger = thisLogger()
}

fun Logger.trace(obj: Any) = trace(obj.toString())
fun Logger.debug(obj: Any) = debug(obj.toString())
fun Logger.info(obj: Any) = info(obj.toString())
fun Logger.warn(obj: Any) = warn(obj.toString())
fun Logger.error(obj: Any) = error(obj.toString())

