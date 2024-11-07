package org.example.encontraoapi.repository

import org.example.encontraoapi.dto.Commission.CommissionGradeTeamDTOMinifiedProjection
import org.example.encontraoapi.dto.Commission.CommissisonDTOMinifiedProjection
import org.example.encontraoapi.entity.Commission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CommissionRepository : JpaRepository<Commission, Long> {
    @Query(
        """SELECT DISTINCT 
    co.id AS commissionID,
    t.id AS teamId,
    co.grade_name_1 AS gradeName1,
    co.grade_name_2 AS gradeName2,
    co.grade_name_3 AS gradeName3,
    co.grade_name_4 AS gradeName4,
    co.grade_name_5 AS gradeName5
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
    commissions co ON ct.id = co.id_competitions_teams
WHERE 
    u.deleted = FALSE
    AND t.deleted = FALSE
    AND c.deleted = FALSE
    AND co.id_user IS NOT NULL
    AND t.id = :teamId;""", nativeQuery = true
    )
    fun findByIdTeam(@Param("teamId") teamId: Long): CommissisonDTOMinifiedProjection

    @Query(
        """
        SELECT 
            c.name AS competition_name,
            cs.institution AS instituicao,
            t.grade AS team_grade,
            array_agg(u.name) AS participants
        FROM 
            competitions c
        JOIN 
            competitions_teams ct ON c.id = ct.id_competition
        JOIN 
            teams t ON ct.id_team = t.id
        JOIN 
            teams_users tu ON t.id = tu.id_team
        JOIN 
            users u ON tu.id_user = u.id
        JOIN 
            campus cs ON u.id_campus = cs.id
        WHERE 
            c.id = :teamId
            AND c.deleted = FALSE
            AND u.deleted = FALSE
        GROUP BY 
            c.id, c.name, cs.institution, t.grade
        ORDER BY 
            t.grade DESC;
    """, nativeQuery = true
    )
    fun findGradeByIdTeam(@Param("teamId") teamId: Long): List<CommissionGradeTeamDTOMinifiedProjection>

}
