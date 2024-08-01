package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "competitions_teams")
class CompetitionsTeams() : BaseEntity() {
    @Column(name = "id_team", nullable = false)
    var idTeam: Long? = null

    @Column(name = "id_competition", nullable = false)
    var idCompetition: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", referencedColumnName = "id", insertable = false, updatable = false)
    var team: Team? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competition", referencedColumnName = "id", insertable = false, updatable = false)
    var competition: Competition? = null
}