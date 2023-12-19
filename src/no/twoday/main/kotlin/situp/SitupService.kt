package no.twoday.situp

class SitupService {
  private val situpIntegration = SitupIntegration()
  private val situpGroups = situpIntegration.getSitupGroups()

  fun findGroupWithMostEvents(): String {
    val groupIds = situpGroups.content.map { Pair(it.id, it.name) }
    val eventsPerGroup =
      groupIds.map { Pair(situpIntegration.getEventsForGroup(it.first), it.second) }
        .map { EventResultList(it.first.content, it.second) }
    val biggest = eventsPerGroup.maxBy { it.situpEventList.size }

    return biggest.situpGroupName
  }

  fun findMostPopularEvent(): MostPopularEvent {
    val groupIds = situpGroups.content.map { Pair(it.id, it.name) }
    val eventsPerGroup =
      groupIds.map { Pair(situpIntegration.getEventsForGroup(it.first), it.second) }
        .map { EventResultList(it.first.content, it.second) }
    val mostPopularSitupEvent = eventsPerGroup
      .flatMap { it.situpEventList }.maxBy { it.attendees.size }

    return MostPopularEvent(
      eventName = mostPopularSitupEvent.description,
      attendees = mostPopularSitupEvent.attendees.size
    )
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

data class EventResultList(val situpEventList: List<SitupEvent>, val situpGroupName: String)

data class MostActiveAttendee(val attendee: SitupAttendee, val attendedEvents: Int)

data class MostPopularEvent(val eventName: String, val attendees: Int)