package no.twoday.situp

import no.twoday.situp.domain.EventResultList
import no.twoday.situp.domain.MostActiveAttendee

class SitupService {
  private val situpIntegration = SitupIntegration()
  private val situpGroups = SitupIntegration().getSitupGroups()

  fun findGroupWithMostEvents(): String {
    val groupIds = situpGroups.content.map { Pair(it.id, it.name) }
    val eventsPerGroup =
      groupIds.map { Pair(situpIntegration.getEventsForGroup(it.first), it.second) }
        .map { EventResultList(it.first.content, it.second) }
    val biggest = eventsPerGroup.maxBy { it.situpEventList.size }

    return biggest.situpGroupName
  }

  fun findMostPopularEvent(): String {
    val groupIds = situpGroups.content.map { Pair(it.id, it.name) }
    val eventsPerGroup =
      groupIds.map { Pair(situpIntegration.getEventsForGroup(it.first), it.second) }
        .map { EventResultList(it.first.content, it.second) }
    val mostPopularSitupEvent = eventsPerGroup
      .flatMap { it.situpEventList }.maxBy { it.attendees.size }
    return mostPopularSitupEvent.description
  }

  fun findMostActiveAttendee(): MostActiveAttendee {
    val eventsForGroups = situpGroups.content
      .map { situpIntegration.getEventsForGroup(it.id).content }

    val mostActiveAttendeeActivity = eventsForGroups.flatMap { event ->
      event.flatMap { it.attendees }
    }.groupBy { attendee -> attendee.id }.maxBy { it.value.size }

    return MostActiveAttendee(
      attendee = mostActiveAttendeeActivity.value.first(),
      attendedEvents = mostActiveAttendeeActivity.value.size
    )
  }
}
