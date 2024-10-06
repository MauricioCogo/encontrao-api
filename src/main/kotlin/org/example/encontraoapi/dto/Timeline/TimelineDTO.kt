package org.example.encontraoapi.dto.Timeline

import java.time.LocalDateTime

interface TimelineDTOMinifiedProjection {
    val name: String?
    val date: LocalDateTime?
    val type: String?
    val local: String?
}

data class TimelineDTO(
    val name: String?,
    val date: LocalDateTime?,
    val type: String?,
    val local: String?,
)

fun TimelineDTOMinifiedProjection.toDTO(): TimelineDTO {
    return TimelineDTO(
        name = this.name,
        date = this.date,
        type = this.type,
        local = this.local,
    )
}