package org.example.encontraoapi.repository

import org.example.encontraoapi.dto.Presentations.PresentationMinifiedProjection
import org.example.encontraoapi.entity.Presentation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PresentationRepository : JpaRepository<Presentation, Long> {
    @Query(
        value = """SELECT 
            p."order" AS "order",
            p."date" AS "date",
            p.entrance_choreography AS entrance,
            p.traditional_dance_1 AS tdance1,
            p.traditional_dance_2 AS tdance2,
            p.traditional_dance_3 AS tdance3,
            p.exit_choreography AS exit,
            p.birivas_dances_1 AS biritiva1,
            p.birivas_dances_2 AS biritiva2,
            p.birivas_dances_3 AS biritiva3,
            c.institution AS campus,
            c.entity AS entity
        FROM 
            presentations p
        JOIN 
            campus c ON p.id = c.id_presentation
        WHERE 
            p.deleted = FALSE AND c.deleted = FALSE
        ORDER BY p."order";""", nativeQuery = true
    )
    fun findAllWithCampus(): List<PresentationMinifiedProjection>
}