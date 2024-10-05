interface UserDetailsMinifiedProjection {
    val participants: List<String>?
    val teamId: Long?
    val competitionId: Long?
    val campus: String?
}

data class UserDetailsDTO(
    val participants: List<String>?,
    val teamId: Long?,
    val competitionId: Long?,
    val campus: String?
)

fun UserDetailsMinifiedProjection.toDTO(): UserDetailsDTO {
    return UserDetailsDTO(
        participants = this.participants,
        teamId = this.teamId,
        competitionId = this.competitionId,
        campus = this.campus
    )
}