package no.twoday.situp.dto

import com.fasterxml.jackson.annotation.JsonProperty

// event
data class SitupEventListDTO(
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