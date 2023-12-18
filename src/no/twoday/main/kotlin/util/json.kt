package no.twoday.util

import com.fasterxml.jackson.databind.ObjectMapper

inline fun <reified T> String.fromJson(): T {
  val objectMapper = ObjectMapper()
  return objectMapper.readValue(this, T::class.java)
}