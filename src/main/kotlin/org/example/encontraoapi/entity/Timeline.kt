package org.example.encontraoapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "timeline")
class Timeline() : BaseEntity() {
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "required", nullable = false)
    var required: Boolean = false

    @Column(name = "date", nullable = false)
    var date: LocalDateTime? = null

    @Column(name = "type", nullable = false)
    var type: String? = null

    @Column(name = "id_point", nullable = false)
    var idPoint: Long? = null

    @Column(name = "id_competition", nullable = false)
    var idCompetition: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_point", referencedColumnName = "id", insertable = false, updatable = false)
    var point: Point? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competition", referencedColumnName = "id", insertable = false, updatable = false)
    var competition: Competition? = null
}
