package org.example.encontraoapi.dto.Commission

interface CommissisonDTOMinifiedProjection {
    val commissionID: Long?
    val teamId: Long?
    val gradeName1: String?
    val gradeName2: String?
    val gradeName3: String?
    val gradeName4: String?
    val gradeName5: String?
}

data class CommissionDTO(
    val commissionID: Long?,
    val teamId: Long?,
    val gradeName1: String?,
    val gradeName2: String?,
    val gradeName3: String?,
    val gradeName4: String?,
    val gradeName5: String?,
)

fun CommissisonDTOMinifiedProjection.toDTO(): CommissionDTO {
    return CommissionDTO(
        commissionID = this.commissionID,
        teamId = this.teamId,
        gradeName1 = this.gradeName1,
        gradeName2 = this.gradeName2,
        gradeName3 = this.gradeName3,
        gradeName4 = this.gradeName4,
        gradeName5 = this.gradeName5,
    )
}