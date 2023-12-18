package no.twoday.situp

import com.fasterxml.jackson.databind.ObjectMapper
import no.twoday.situp.dto.SitupEventListDTO
import no.twoday.situp.dto.SitupGroupListDTO
import no.twoday.util.Constants.apiKey
import no.twoday.util.fromJson
import okhttp3.OkHttpClient
import okhttp3.Request

class SitupIntegration {
  private val okHttpClient = OkHttpClient()

  fun getSitupGroups(): SitupGroupListDTO {
    val request = Request.Builder()
      .url("https://twoday-internal.com/kandidat/api/situp?size=100")
      .addHeader("Authorization", apiKey)
      .build()

    okHttpClient.newCall(request).execute().use { response ->
      if (!response.isSuccessful) {
        throw IllegalStateException(
          """
          Error occur while calling the api, check if everything is OK, ERROR: ${response.code}
          """.trimIndent()
        )
      }
      val responseJson = response.body?.string() ?: throw Exception("EMPTY")
      return responseJson.fromJson<SitupGroupListDTO>()
    }
  }

  fun getEventsForGroup(id: String): SitupEventListDTO {
    val request = Request.Builder()
      .url("https://twoday-internal.com/kandidat/api/situp/${id}/events?size=100")
      .addHeader("Authorization", apiKey)
      .build()

    okHttpClient.newCall(request).execute().use { response ->
      if (!response.isSuccessful) {
        throw IllegalStateException(
          """
          Error occur while calling the api, check if everything is OK, ERROR: ${response.code}
          """.trimIndent()
        )
      }
      val responseJson = response.body?.string() ?: throw Exception("EMPTY")
      return responseJson.fromJson<SitupEventListDTO>()
    }
  }
}
