package no.twoday.situp

import com.fasterxml.jackson.annotation.JsonProperty
import no.twoday.util.Constants.apiKey
import no.twoday.util.fromJson
import okhttp3.OkHttpClient
import okhttp3.Request

class SitupIntegration {
  private val okHttpClient = OkHttpClient()

  fun getSitupGroups(): SitupGroupsDTO {
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
      return responseJson.fromJson<SitupGroupsDTO>()
    }
  }

  fun getEventsForGroup(id: String): SitupEventsDTO {
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
      return responseJson.fromJson<SitupEventsDTO>()
    }
  }
}

// Situp group
data class SitupGroupsDTO(
  @JsonProperty("content") val content: List<SitupGroup>,
  @JsonProperty("totalElements") val totalElements: Int,
  @JsonProperty("pageNumber") val pageSize: Int,
  @JsonProperty("totalPages") val totalPages: Int,
  @JsonProperty("first") val first: Boolean,
  @JsonProperty("last") val last: Boolean,
  @JsonProperty("empty") val empty: Boolean,
)

data class SitupGroup(
  @JsonProperty("id") val id: String,
  @JsonProperty("name") val name: String,
  @JsonProperty("responsible") val responsible: SitupResponsiblePerson
)

data class SitupResponsiblePerson(
  @JsonProperty("id") val id: String,
  @JsonProperty("name") val name: String,
  @JsonProperty("email") val email: String,
  @JsonProperty("title") val title: String,
  @JsonProperty("department") val department: String,
)

// Situp event


data class SitupEventsDTO(
  @JsonProperty("content") val content: List<SitupEvent>,
  @JsonProperty("totalElements") val totalElements: Int,
  @JsonProperty("pageNumber") val pageNumber: Int,
  @JsonProperty("pageSize") val pageSize: Int,
  @JsonProperty("totalPages") val totalPages: Int,
  @JsonProperty("first") val first: Boolean,
  @JsonProperty("last") val last: Boolean,
  @JsonProperty("empty") val empty: Boolean,
)

data class SitupEvent(
  @JsonProperty("id") val id: String,
  @JsonProperty("description") val description: String,
  @JsonProperty("foodAlternatives") val foodAlternatives: List<String>,
  @JsonProperty("attendees") val attendees: List<SitupAttendee>,
  @JsonProperty("address") val address: String,
  @JsonProperty("date") val date: String,
  @JsonProperty("imageUrl") val imageUrl: String,
  @JsonProperty("openSpots") val openSpots: Int,
)

data class SitupAttendee(
  @JsonProperty("id") val id: String,
  @JsonProperty("name") val name: String,
  @JsonProperty("email") val email: String,
  @JsonProperty("title") val title: String,
  @JsonProperty("department") val department: String,
)