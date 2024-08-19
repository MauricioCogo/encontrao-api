package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Competition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CompetitionRepository : JpaRepository<Competition, Long>{
    @Query(
        """
            SELECT t.id 
            FROM competitions_teams ct
            JOIN teams t ON t.id = ct.id_team
            WHERE ct.id_competition = :competitionId
        """,
        nativeQuery = true
    )
    fun findTeamByCompetitionId(competitionId : Long) : List<Long>

}
