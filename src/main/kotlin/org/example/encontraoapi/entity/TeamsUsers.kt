package org.example.encontraoapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "teams_users")
data class TeamUser(
    @Column(name = "id_user", nullable = false)
    val idUser: Long,

    @Column(name = "id_team", nullable = false)
    val idTeam: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", referencedColumnName = "id", insertable = false, updatable = false)
    val team: Team
) : BaseEntity()
