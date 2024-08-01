package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User() : BaseEntity() {
    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false, unique = true, length = 11)
    var cpf: String? = null

    @Column(nullable = false)
    var registration: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(nullable = false)
    var roles: String? = null

    @Column(name = "is_evaluator", nullable = false)
    var isEvaluator: Boolean = false

    @Column(name = "is_admin", nullable = false)
    var isAdmin: Boolean = false

    @ManyToOne
    @JoinColumn(name = "id_campus", referencedColumnName = "id")
    var campus: Campus? = null
}
