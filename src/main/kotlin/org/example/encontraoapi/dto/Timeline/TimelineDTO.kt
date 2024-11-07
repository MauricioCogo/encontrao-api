package org.example.encontraoapi.dto.Timeline

import java.time.LocalDateTime

interface TimelineDTOMinifiedProjection {
    val name: String?
    val initialTime: LocalDateTime?
    val endTime: LocalDateTime?
    val type: String?
    val local: String?
}

data class TimelineDTO(
    val name: String?,
    val initialTime: LocalDateTime?,
    val endTime: LocalDateTime?,
    val type: String?,
    val local: String?,
)

fun TimelineDTOMinifiedProjection.toDTO(): TimelineDTO {
    return TimelineDTO(
        name = this.name,
        initialTime = this.initialTime,
        endTime = this.endTime,
        type = this.type,
        local = this.local,
    )
}