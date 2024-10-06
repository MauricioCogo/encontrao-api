package org.example.encontraoapi.service

import UserDetailsDTO
import org.example.encontraoapi.entity.CompetitionsTeams
import org.example.encontraoapi.repository.CompetitionsTeamsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toDTO
import java.time.LocalDateTime

@Service
class CompetitionsTeamsService @Autowired constructor(
    private val competitionsTeamsRepository: CompetitionsTeamsRepository
) {
    fun getAll(): List<CompetitionsTeams> {
        try {
            return competitionsTeamsRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): CompetitionsTeams? {
        try {
            return competitionsTeamsRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getByCompetition(id_competi: Long): List<UserDetailsDTO>? {
        return try {
            competitionsTeamsRepository.findStudentDetailsByCompetitionId(id_competi)?.map { it.toDTO() }
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getAllParticipantsByCompetition(id_competi: Long): List<UserDetailsDTO>? {
        return try {
            competitionsTeamsRepository.findParticipantsByCompetition(id_competi)?.map { it.toDTO() }
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(competitionsTeams: CompetitionsTeams): CompetitionsTeams {
        try {
            return competitionsTeamsRepository.save(competitionsTeams)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, competitionsTeamsDetails: CompetitionsTeams): CompetitionsTeams? {
        try {
            val competitionsTeams = competitionsTeamsRepository.findById(id).orElse(null) ?: return null

            val updatedUser = CompetitionsTeams().also {
                it.id = competitionsTeams.id
                it.idTeam = competitionsTeamsDetails.idTeam
                it.idCompetition = competitionsTeamsDetails.idCompetition
                it.team = competitionsTeamsDetails.team
                it.competition = competitionsTeamsDetails.competition
            }

            return competitionsTeamsRepository.save(updatedUser)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var competitionsTeams = competitionsTeamsRepository.findById(id).orElse(null)

            competitionsTeams.updated_by = 1
            competitionsTeams.updated = LocalDateTime.now()
            competitionsTeams.deleted = true

            competitionsTeamsRepository.save(competitionsTeams)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
