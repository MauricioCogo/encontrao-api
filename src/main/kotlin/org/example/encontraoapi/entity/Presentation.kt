package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "presentations")
class Presentation() : BaseEntity() {

    @Column(name = "order")
    var order: Int? = null

    @Column(name = "entrance_choreography")
    var entranceChoreography: String? = null

    @Column(name = "traditional_dance_1", nullable = false)
    var traditionalDance1: String? = null

    @Column(name = "traditional_dance_2", nullable = false)
    var traditionalDance2: String? = null

    @Column(name = "traditional_dance_3", nullable = false)
    var traditionalDance3: String? = null

    @Column(name = "traditional_dance_extra", nullable = false)
    var traditionalDanceExtra: String? = null

    @Column(name = "exit_choreography")
    var exitChoreography: String? = null

    @Column(name = "birivas_dances_1")
    var birivasDance1: String? = null

    @Column(name = "birivas_dances_2")
    var birivasDance2: String? = null

    @Column(name = "birivas_dances_3")
    var birivasDance3: String? = null
}
