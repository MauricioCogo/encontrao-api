package org.example.encontraoapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "timeline")
data class Timeline(
    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "required", nullable = false)
    val required: Boolean = false,

    @Column(name = "date", nullable = false)
    val date: LocalDateTime,

    @Column(name = "id_point", nullable = false)
    val idPoint: Long,

    @Column(name = "id_competition", nullable = false)
    val idCompetition: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_point", referencedColumnName = "id", insertable = false, updatable = false)
    val point: Point,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competition", referencedColumnName = "id", insertable = false, updatable = false)
    val competition: Competition
) : BaseEntity()
