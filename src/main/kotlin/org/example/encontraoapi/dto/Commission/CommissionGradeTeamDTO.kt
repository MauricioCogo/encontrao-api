package org.example.encontraoapi.dto.Commission

interface CommissionGradeTeamDTOMinifiedProjection {
    val competitionName: String?
    val instituicao: String?
    val teamGrade: String?
    val participants: List<String>?
}

data class CommissionGradeTeamDTO(
    override val competitionName: String?,
    override val instituicao: String?,
    override val teamGrade: String?,
    override val participants: List<String>?
) : CommissionGradeTeamDTOMinifiedProjection

fun CommissionGradeTeamDTOMinifiedProjection.toDTO(): CommissionGradeTeamDTO {
    return CommissionGradeTeamDTO(
        competitionName = this.competitionName,
        instituicao = this.instituicao,
        teamGrade = this.teamGrade,
        participants = this.participants
    )
}
