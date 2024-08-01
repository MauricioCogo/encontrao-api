package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointRepository : JpaRepository<Point, Long>
