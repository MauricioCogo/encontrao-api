package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "teams")
class Team() : BaseEntity() {
    @Column(name = "grade", precision = 10, scale = 2)
    var grade: BigDecimal? = null
}
