package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.TeamsUsers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamsUsersRepository : JpaRepository<TeamsUsers, Long>
