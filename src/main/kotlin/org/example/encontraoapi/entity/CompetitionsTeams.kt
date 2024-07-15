package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "competitions_teams")
data class CompetitionTeam(
    @Column(name = "id_team", nullable = false)
    val idTeam: Long,

    @Column(name = "id_competition", nullable = false)
    val idCompetition: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", referencedColumnName = "id", insertable = false, updatable = false)
    val team: Team,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competition", referencedColumnName = "id", insertable = false, updatable = false)
    val competition: Competition
) : BaseEntity()
