package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "teams")
data class Team(
    @Column(name = "grade", precision = 10, scale = 2)
    val grade: BigDecimal?
) : BaseEntity()
