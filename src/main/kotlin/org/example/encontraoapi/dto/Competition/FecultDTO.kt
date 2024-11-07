interface FecultMinifiedProjection {
    val participants: List<String>?
    val competitionName: String?
    val competitionId: Long?
    val campus: String?
}

data class FecultDTO(
    val participants: List<String>?,
    val competitionName: String?,
    val competitionId: Long?,
    val campus: String?
)

fun FecultMinifiedProjection.toDTO(): FecultDTO {
    return FecultDTO(
        participants = this.participants,
        competitionName = this.competitionName,
        competitionId = this.competitionId,
        campus = this.campus
    )
}