package no.twoday.situp.domain

import no.twoday.situp.dto.SitupEvent

data class EventResultList(val situpEventList: List<SitupEvent>, val situpGroupName: String)