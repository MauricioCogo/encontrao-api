package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Timeline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimelineRepository : JpaRepository<Timeline, Long>
