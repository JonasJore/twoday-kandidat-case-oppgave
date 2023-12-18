package no.twoday.situp.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class SitupGroupListDTO(
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