package org.example.encontraoapi.application

import FecultDTO
import UserDetailsDTO
import org.example.encontraoapi.entity.CompetitionsTeams
import org.example.encontraoapi.service.CompetitionsTeamsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CompetitionsTeamsApplication @Autowired constructor(
    private val competitionsTeamsService: CompetitionsTeamsService
) {

    fun getAll(): List<CompetitionsTeams> {
        return competitionsTeamsService.getAll()
    }

    fun getById(id: Long): CompetitionsTeams? {
        return competitionsTeamsService.getById(id)
    }

    fun getByCompetition(id_competi: Long): List<UserDetailsDTO>? {
        return competitionsTeamsService.getByCompetition(id_competi)
    }

    fun getByFecult(): List<FecultDTO>? {
        return competitionsTeamsService.getByFecult()
    }

    fun getAllParticipantsByCompetition(id_competi: Long): List<UserDetailsDTO>? {
        return competitionsTeamsService.getAllParticipantsByCompetition(id_competi)
    }

    fun create(competitionsTeams: CompetitionsTeams): CompetitionsTeams {
        try {
            if (competitionsTeams.idTeam == null) {
                throw Exception("id team can not be empty")
            }

            if (competitionsTeams.idCompetition == null) {
                throw Exception("id competition name can not be empty")
            }

            return competitionsTeamsService.create(competitionsTeams)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, competitionsTeamsDetails: CompetitionsTeams): CompetitionsTeams? {
        return competitionsTeamsService.update(id, competitionsTeamsDetails)
    }

    fun delete(id: Long): Boolean {
        return competitionsTeamsService.delete(id)
    }
}
