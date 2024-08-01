package org.example.encontraoapi.service

import org.example.encontraoapi.entity.Team
import org.example.encontraoapi.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TeamService @Autowired constructor(
    private val teamRepository: TeamRepository
) {
    fun getAll(): List<Team> {
        try {
            return teamRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Team? {
        try {
            return teamRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(team: Team): Team {
        try {
            return teamRepository.save(team)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, teamDetails: Team): Team? {
        try {
            val team = teamRepository.findById(id).orElse(null) ?: return null

            val updatedTeam = Team().also {
                it.id = team.id
                it.grade = teamDetails.grade
            }

            return teamRepository.save(updatedTeam)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var team = teamRepository.findById(id).orElse(null)

            team.updated_by = 1
            team.updated = LocalDateTime.now()
            team.deleted = true

            teamRepository.save(team)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
