package no.twoday

import no.twoday.situp.SitupService

fun main() {
  // task 1
  println("Situpgroup with the most events is: ${SitupService().findGroupWithMostEvents()}")
  // task 2
  println("The most popular situp event is: ${SitupService().findMostPopularEvent()}")
  // task 3
  val mostActiveAttendee = SitupService().findMostActiveAttendee()
  println("The most active attendee is ${mostActiveAttendee.attendee.name}, which has attended ${mostActiveAttendee.attendedEvents} events")
}