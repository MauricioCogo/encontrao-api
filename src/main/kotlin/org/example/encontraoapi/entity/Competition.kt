package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "competitions")
class Competition() : BaseEntity() {
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "modality", nullable = false)
    var modality: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "festival_event", nullable = false)
    var festivalEvent: Boolean = false

    @Column(name = "participants", nullable = false)
    var participants: Int? = null

    @Column(name = "commission", nullable = false)
    var commission: Boolean = false

    @Column(name = "id_point", nullable = false)
    var idPoint: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_point", referencedColumnName = "id", insertable = false, updatable = false)
    var point: Point? = null
}

