package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.Commission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommissionRepository : JpaRepository<Commission, Long>
