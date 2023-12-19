package no.twoday

import no.twoday.situp.SitupService

fun main() {
  val situpService = SitupService()
  // task 1
  println("Situpgroup with the most events is: ${SitupService().findGroupWithMostEvents()}")
  // task 2
  val mostPopularEvent = situpService.findMostPopularEvent()
  println("The most popular situp event is: ${mostPopularEvent.eventName}, with ${mostPopularEvent.attendees} attendees")
  // task 3
  val mostActiveAttendee = situpService.findMostActiveAttendee()
  println("The most active attendee is ${mostActiveAttendee.attendee.name}, which has attended ${mostActiveAttendee.attendedEvents} events")
}