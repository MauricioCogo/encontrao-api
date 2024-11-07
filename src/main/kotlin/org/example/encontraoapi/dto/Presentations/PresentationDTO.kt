package org.example.encontraoapi.dto.Presentations

import java.time.LocalDateTime

interface PresentationMinifiedProjection {
    val order: Int?
    val date: LocalDateTime?
    val entrance: String?
    val tdance1: String?
    val tdance2: String?
    val tdance3: String?
    val exit: String?
    val biritiva1: String?
    val biritiva2: String?
    val biritiva3: String?
    val campus: String?
    val entity: String?

}

data class PresentationDTO(
    val order: Int?,
    val date: LocalDateTime?,
    val entrance: String?,
    val tdance1: String?,
    val tdance2: String?,
    val tdance3: String?,
    val exit: String?,
    val biritiva1: String?,
    val biritiva2: String?,
    val biritiva3: String?,
    val campus: String?,
    val entity: String?,
)

fun PresentationMinifiedProjection.toDTO(): PresentationDTO {
    return PresentationDTO(
        order = this.order,
        date = this.date,
        entrance = this.entrance,
        tdance1 = this.tdance1,
        tdance2 = this.tdance2,
        tdance3 = this.tdance3,
        exit = this.exit,
        biritiva1 = this.biritiva1,
        biritiva2 = this.biritiva2,
        biritiva3 = this.biritiva3,
        campus = this.campus,
        entity = this.entity,
    )
}