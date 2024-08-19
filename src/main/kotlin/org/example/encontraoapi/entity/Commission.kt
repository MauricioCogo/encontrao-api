package org.example.encontraoapi.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "commissions")
class Commission : BaseEntity() {

    @Column(name = "id_competitions_teams", nullable = false)
    var idCompetitionsTeams: Long? = null

    @Column(name = "id_user", nullable = false)
    var idUser: Long? = null

    @Column(name = "grade_1", nullable = false, precision = 10, scale = 2)
    var grade1: BigDecimal = BigDecimal.ZERO

    @Column(name = "grade_2", nullable = false, precision = 10, scale = 2)
    var grade2: BigDecimal = BigDecimal.ZERO

    @Column(name = "grade_3", nullable = false, precision = 10, scale = 2)
    var grade3: BigDecimal = BigDecimal.ZERO

    @Column(name = "grade_4", nullable = false, precision = 10, scale = 2)
    var grade4: BigDecimal = BigDecimal.ZERO

    @Column(name = "grade_5", precision = 10, scale = 2)
    var grade5: BigDecimal? = BigDecimal.ZERO

    @Column(name = "grade_name_1", nullable = false)
    var nameGrade1: String? = null

    @Column(name = "grade_name_2", nullable = false)
    var nameGrade2: String? = null

    @Column(name = "grade_name_3", nullable = false)
    var nameGrade3: String? = null

    @Column(name = "grade_name_4", nullable = false)
    var nameGrade4: String? = null

    @Column(name = "grade_name_5")
    var nameGrade5: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competitions_teams", referencedColumnName = "id", insertable = false, updatable = false)
    var competitionsTeams: CompetitionsTeams? = null
}
