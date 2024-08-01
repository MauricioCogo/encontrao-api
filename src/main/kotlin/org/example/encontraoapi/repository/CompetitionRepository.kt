package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Competition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompetitionRepository : JpaRepository<Competition, Long>
