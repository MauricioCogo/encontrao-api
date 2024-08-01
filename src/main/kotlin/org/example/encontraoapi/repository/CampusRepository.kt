package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Campus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampusRepository : JpaRepository<Campus, Long>
