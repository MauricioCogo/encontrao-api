package org.example.encontraoapi.dto

import java.math.BigDecimal

data class UpdateCommissionAndTeamRequest(
    val commission: CommissionRequest,
    val team: TeamRequest
)

data class CommissionRequest(
    val idCompetitionsTeams: Long,
    val idUser: Long,
    val grade1: BigDecimal,
    val grade2: BigDecimal,
    val grade3: BigDecimal,
    val grade4: BigDecimal,
    val grade5: BigDecimal?
)

data class TeamRequest(
    val id: Long,
    val grade: BigDecimal
)
