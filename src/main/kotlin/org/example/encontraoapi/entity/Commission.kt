package org.example.encontraoapi.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "commission")
class Commission() : BaseEntity() {
    @Column(name = "id_competitions_teams", nullable = false)
    var idCompetitionsTeams: Long? = null

    @Column(name = "id_user", nullable = false)
    var idUser: Long? = null

    @Column(name = "grade_1", nullable = false, precision = 10, scale = 2)
    var grade1: BigDecimal? = null

    @Column(name = "grade_2", nullable = false, precision = 10, scale = 2)
    var grade2: BigDecimal? = null

    @Column(name = "grade_3", nullable = false, precision = 10, scale = 2)
    var grade3: BigDecimal? = null

    @Column(name = "grade_4", nullable = false, precision = 10, scale = 2)
    var grade4: BigDecimal? = null

    @Column(name = "grade_5", precision = 10, scale = 2)
    var grade5: BigDecimal? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competitions_teams", referencedColumnName = "id", insertable = false, updatable = false)
    var competitionsTeams: CompetitionsTeams? = null;

}