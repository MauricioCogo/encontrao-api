package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
data class Presentation(
    @Column(name = "entrance_choreography")
    val entranceChoreography: String? = null,

    @Column(name = "traditional_dance_1", nullable = false)
    val traditionalDance1: String,

    @Column(name = "traditional_dance_2", nullable = false)
    val traditionalDance2: String,

    @Column(name = "traditional_dance_3", nullable = false)
    val traditionalDance3: String,

    @Column(name = "exit_choreography")
    val exitChoreography: String? = null,

    @Column(name = "birivas_dances_1")
    val birivasDance1: String? = null,

    @Column(name = "birivas_dances_2")
    val birivasDance2: String? = null,

    @Column(name = "birivas_dances_3")
    val birivasDance3: String? = null
) : BaseEntity()
