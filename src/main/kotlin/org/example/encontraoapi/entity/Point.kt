package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "points")
class Point() : BaseEntity() {
    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "type", nullable = false)
    var type: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "icon", nullable = false)
    var icon: String? = null

    @Column(name = "size", nullable = false)
    var size: Int? = null

    @Column(name = "latitude", nullable = false)
    var latitude: String? = null

    @Column(name = "longitude", nullable = false)
    var longitude: String? = null
}
