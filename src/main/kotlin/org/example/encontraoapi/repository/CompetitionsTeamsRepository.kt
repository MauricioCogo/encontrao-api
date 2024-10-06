package org.example.encontraoapi.repository

import UserDetailsMinifiedProjection
import org.example.encontraoapi.entity.CompetitionsTeams
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CompetitionsTeamsRepository : JpaRepository<CompetitionsTeams, Long> {

    @Query(
        """
        SELECT 
            array_agg(u.name) AS participants,
            t.id AS teamId,
            c.id AS competitionId,
            cam.institution AS campus
        FROM 
            users u
        JOIN 
            teams_users tu ON u.id = tu.id_user
        JOIN 
            teams t ON tu.id_team = t.id
        JOIN 
            competitions_teams ct ON t.id = ct.id_team
        JOIN 
            competitions c ON ct.id_competition = c.id
        JOIN 
            campus cam ON u.id_campus = cam.id
        WHERE 
            c.commission = TRUE 
            AND c.id = :competitionId
        GROUP BY 
            t.id, c.id, cam.institution
    """,
        nativeQuery = true
    )
    fun findStudentDetailsByCompetitionId(@Param("competitionId") competitionId: Long): List<UserDetailsMinifiedProjection>?

    @Query(
        """
        SELECT 
            array_agg(u.name) AS participants,
            t.id AS teamId,
            c.id AS competitionId,
            cam.institution AS campus
        FROM 
            users u
        JOIN 
            teams_users tu ON u.id = tu.id_user
        JOIN 
            teams t ON tu.id_team = t.id
        JOIN 
            competitions_teams ct ON t.id = ct.id_team
        JOIN 
            competitions c ON ct.id_competition = c.id
        JOIN 
            campus cam ON u.id_campus = cam.id
        WHERE 
            c.id = :competitionId
        GROUP BY 
            t.id, c.id, cam.institution
    """,
        nativeQuery = true
    )
    fun findParticipantsByCompetition(@Param("competitionId") competitionId: Long): List<UserDetailsMinifiedProjection>?
}
