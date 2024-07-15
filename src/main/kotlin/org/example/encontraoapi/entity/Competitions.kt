package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "competitions")
data class Competition(
    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "modality", nullable = false)
    val modality: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "festival_event", nullable = false)
    val festivalEvent: Boolean = false,

    @Column(name = "participants", nullable = false)
    val participants: Int,

    @Column(name = "commission", nullable = false)
    val commission: Boolean = false,

    @Column(name = "id_point", nullable = false)
    val idPoint: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_point", referencedColumnName = "id", insertable = false, updatable = false)
    val point: Point
) : BaseEntity()
