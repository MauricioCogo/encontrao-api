package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User() : BaseEntity() {
    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false, unique = true, length = 11)
    var cpf: String = ""

    @Column(nullable = false)
    var registration: String = ""

    @Column(nullable = false)
    var password: String = ""

    @Column(nullable = false)
    var roles: String = ""

    @Column(name = "is_evaluator", nullable = false)
    var isEvaluator: Boolean = false

    @Column(name = "is_admin", nullable = false)
    var isAdmin: Boolean = false

    @ManyToOne
    @JoinColumn(name = "id_campus", referencedColumnName = "id")
    var campus: Campus? = null
}
