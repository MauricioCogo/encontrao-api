package org.example.encontraoapi.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "commission")
data class Commission(
    @Column(name = "id_competitions_teams", nullable = false)
    val idCompetitionsTeams: Long,

    @Column(name = "id_user", nullable = false)
    val idUser: Long,

    @Column(name = "grade_1", nullable = false, precision = 10, scale = 2)
    val grade1: BigDecimal?,

    @Column(name = "grade_2", nullable = false, precision = 10, scale = 2)
    val grade2: BigDecimal?,

    @Column(name = "grade_3", nullable = false, precision = 10, scale = 2)
    val grade3: BigDecimal?,

    @Column(name = "grade_4", nullable = false, precision = 10, scale = 2)
    val grade4: BigDecimal?,

    @Column(name = "grade_5", nullable = false, precision = 10, scale = 2)
    val grade5: BigDecimal?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competitions_teams", referencedColumnName = "id", insertable = false, updatable = false)
    val competitionsTeams: CompetitionTeam
) : BaseEntity()
