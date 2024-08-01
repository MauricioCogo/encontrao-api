package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "campus")
class Campus() : BaseEntity() {
    @Column(name = "institution", nullable = false)
    var institution: String? = null

    @Column(name = "coordinator_name", length = 11, nullable = false)
    var coordinatorName: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "entity", nullable = false)
    var entity: String? = null

    @Column(name = "dormitory", nullable = false)
    var dormitory: String? = null

    @Column(name = "id_presentation", nullable = false)
    var idPresentation: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presentation", referencedColumnName = "id", insertable = false, updatable = false)
    var presentation: Presentation = Presentation()
}
