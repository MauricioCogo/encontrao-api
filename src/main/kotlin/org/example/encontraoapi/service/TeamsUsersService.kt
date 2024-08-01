package org.example.encontraoapi.service

import org.example.encontraoapi.entity.TeamsUsers
import org.example.encontraoapi.repository.TeamsUsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TeamsUsersService @Autowired constructor(
    private val teamUsersRepository: TeamsUsersRepository
) {
    fun getAll(): List<TeamsUsers> {
        try {
            return teamUsersRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): TeamsUsers? {
        try {
            return teamUsersRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(teamUsers: TeamsUsers): TeamsUsers {
        try {
            return teamUsersRepository.save(teamUsers)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, teamUsersDetails: TeamsUsers): TeamsUsers? {
        try {
            val teamUsers = teamUsersRepository.findById(id).orElse(null) ?: return null

            val updatedTeamsUsers = TeamsUsers().also {
                it.id = teamUsers.id
                it.idUser = teamUsersDetails.idUser
                it.idTeam = teamUsersDetails.idTeam
                it.user = teamUsersDetails.user
                it.team = teamUsersDetails.team
            }

            return teamUsersRepository.save(updatedTeamsUsers)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var teamUsers = teamUsersRepository.findById(id).orElse(null)

            teamUsers.updated_by = 1
            teamUsers.updated = LocalDateTime.now()
            teamUsers.deleted = true

            teamUsersRepository.save(teamUsers)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
