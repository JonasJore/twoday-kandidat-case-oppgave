package no.twoday.situp.domain

import no.twoday.situp.dto.SitupAttendee

data class MostActiveAttendee(val attendee: SitupAttendee, val attendedEvents: Int)