package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true, length = 11)
    val cpf: String,

    @Column(nullable = false)
    val registration: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val roles: String,

    @Column(name = "is_evaluator", nullable = false)
    val isEvaluator: Boolean = false,

    @Column(name = "is_admin", nullable = false)
    val isAdmin: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "id_campus", referencedColumnName = "id")
    val campus: Campus? = null
) : BaseEntity()
