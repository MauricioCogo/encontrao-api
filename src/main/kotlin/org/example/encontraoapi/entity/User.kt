package org.example.encontraoapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Entity
@Table(name = "users")
class User() : BaseEntity() {

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false, unique = true, length = 11)
    var cpf: String? = null

    @Column(nullable = false)
    var avatar: String? = null

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

    @Column(name = "id_campus", nullable = false)
    var campusId: Long? = null

    // Returns a list of authorities based on roles and flags
    fun getAuthorities(): List<SimpleGrantedAuthority> {
        val authorities = mutableListOf<SimpleGrantedAuthority>()

        // Add roles from the 'roles' field
        roles?.split(",")?.forEach {
            authorities.add(SimpleGrantedAuthority("ROLE_$it"))
        }

        if (isEvaluator) {
            authorities.add(SimpleGrantedAuthority("ROLE_EVALUATOR"))
        }
        if (isAdmin) {
            authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        }

        return authorities
    }
}
