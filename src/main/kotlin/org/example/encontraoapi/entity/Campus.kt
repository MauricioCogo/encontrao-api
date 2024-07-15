package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "campus")
data class Campus(
    @Column(name = "institution", nullable = false)
    val institution: String,

    @Column(name = "coordinator_name", length = 11, nullable = false)
    val coordinatorName: String,

    @Column(name = "registration", nullable = false)
    val registration: String,

    @Column(name = "entity", nullable = false)
    val entity: String,

    @Column(name = "dormitory", nullable = false)
    val dormitory: String,

    @Column(name = "id_presentation", nullable = false)
    val idPresentation: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presentation", referencedColumnName = "id", insertable = false, updatable = false)
    val presentation: Presentation
) : BaseEntity()
