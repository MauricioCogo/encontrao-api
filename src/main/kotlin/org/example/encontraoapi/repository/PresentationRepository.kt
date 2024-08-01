package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Presentation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PresentationRepository : JpaRepository<Presentation, Long>
