package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PointRepository : JpaRepository<Point, Long> {

    @Query(value = "SELECT * FROM points WHERE type = :type", nativeQuery = true)
    fun findByType(@Param("type") type: String): List<Point>

}