package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "points")
data class Point(
    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "icon", nullable = false)
    val icon: String,

    @Column(name = "latitude", nullable = false)
    val latitude: String,

    @Column(name = "longitude", nullable = false)
    val longitude: String
) : BaseEntity()
