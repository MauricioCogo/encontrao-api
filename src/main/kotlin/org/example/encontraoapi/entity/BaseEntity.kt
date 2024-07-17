package org.example.encontraoapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "deleted")
    var deleted: Boolean = false,

    @Column(name = "created")
    var created: LocalDateTime? = null,

    @Column(name = "created_by")
    var created_by: Long? = null,

    @Column(name = "updated")
    var updated: LocalDateTime? = null,

    @Column(name = "updated_by")
    var updated_by: Long? = null,





    )