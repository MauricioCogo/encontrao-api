package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "teams_users")
class TeamsUsers() : BaseEntity() {
    @Column(name = "id_user", nullable = false)
    var idUser: Long? = null

    @Column(name = "id_team", nullable = false)
    var idTeam: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", referencedColumnName = "id", insertable = false, updatable = false)
    var team: Team? = null
}