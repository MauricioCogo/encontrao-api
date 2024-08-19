package org.example.encontraoapi.application

import org.example.encontraoapi.dto.Competition.ParticipantsDTO
import org.example.encontraoapi.entity.Competition
import org.example.encontraoapi.service.CompetitionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CompetitionApplication @Autowired constructor(
    private val competitionService: CompetitionService
) {

    fun getAll(): List<Competition> {
        return competitionService.getAll()
    }

    fun getById(id: Long): Competition?{
        return competitionService.getById(id)
    }

    fun getUserByCompetitionId(id: Long): List<ParticipantsDTO>{
        return competitionService.getUserByCompetitionId(id)
    }

    fun create(competition: Competition): Competition {
        try {
            if(competition.name.isNullOrEmpty()){
                throw Exception("name can not be empty")
            }

            if(competition.modality.isNullOrEmpty()){
                throw Exception("modality can not be empty")
            }

            if(competition.description.isNullOrEmpty()){
                throw Exception("description can not be empty")
            }

            if(competition.participants == null){
                throw Exception("participants can not be empty")
            }

            if(competition.idPoint == null){
                throw Exception("id point can not be empty")
            }

            return competitionService.create(competition)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, competitionDetails: Competition): Competition? {
        return competitionService.update(id, competitionDetails)
    }

    fun delete(id: Long): Boolean {
        return competitionService.delete(id)
    }
}
