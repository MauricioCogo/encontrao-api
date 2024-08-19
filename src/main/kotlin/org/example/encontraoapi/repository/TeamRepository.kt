package org.example.encontraoapi.repository

import org.example.encontraoapi.dto.Competition.ParticipantsMinifiedProjection
import org.example.encontraoapi.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository : JpaRepository<Team, Long>{
    @Query(
        """
            SELECT
                u.id AS "id",
                u.name AS "name",
                u.registration AS "registration",
                u.roles AS "roles"
            FROM teams_users tu 
            JOIN users u ON u.id = tu.id_user
            WHERE tu.id IN (:teamIds)
        """,
        nativeQuery = true
    )
    fun findParticipantsByTeamId(teamIds: List<Long> = listOf()): List<ParticipantsMinifiedProjection>
}
