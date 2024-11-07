package org.example.encontraoapi.repository

import org.example.encontraoapi.dto.Timeline.TimelineDTOMinifiedProjection
import org.example.encontraoapi.entity.Timeline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TimelineRepository : JpaRepository<Timeline, Long> {

    @Query(
        value = """
            SELECT 
                t.name AS name, 
                t.type AS type, 
                t."date" AS initialTime,  -- Use as aspas duplas para garantir a referência correta
                t.end_date AS endTime,
                NULL AS local  -- Adiciona uma coluna NULL para manter a mesma estrutura
            FROM 
                timeline t
            WHERE 
                t.required = true
            
            UNION ALL
            
            SELECT DISTINCT 
                t.name AS name, 
                t.type AS type, 
                t."date" AS initialTime,  -- Use as aspas duplas aqui também
                t.end_date AS endTime,
                p.description AS local  -- Nome do ponto
            FROM 
                timeline t
            JOIN 
                competitions c ON t.id_competition = c.id
            JOIN 
                competitions_teams ct ON c.id = ct.id_competition
            JOIN 
                teams_users tu ON ct.id_team = tu.id_team
            JOIN 
                points p ON c.id_point = p.id   -- Juntando a tabela points para obter o local
            WHERE 
                tu.id_user = ? 
            ORDER BY 
                initialTime  -- Use o alias correto para evitar ambiguidades

        """, nativeQuery = true
    )
    fun findAllByIdUser(@Param("userId") userId: Long): List<TimelineDTOMinifiedProjection>
}
