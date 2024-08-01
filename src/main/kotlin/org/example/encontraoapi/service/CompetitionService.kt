package org.example.encontraoapi.service

import org.example.encontraoapi.entity.Competition
import org.example.encontraoapi.repository.CompetitionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CompetitionService @Autowired constructor(
    private val competitionRepository: CompetitionRepository
) {
    fun getAll(): List<Competition> {
        try {
            return competitionRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Competition? {
        try {
            return competitionRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(competition: Competition): Competition {
        try {
            return competitionRepository.save(competition)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, competitionDetails: Competition): Competition? {
        try {
            val competition = competitionRepository.findById(id).orElse(null) ?: return null

            val updatedCompetition = Competition().also {
                it.id = competition.id
                it.name = competitionDetails.name
                it.modality = competitionDetails.modality
                it.description = competitionDetails.description
                it.festivalEvent = competitionDetails.festivalEvent
                it.participants = competitionDetails.participants
                it.commission = competitionDetails.commission
                it.idPoint = competitionDetails.idPoint
                it.point = competitionDetails.point
            }

            return competitionRepository.save(updatedCompetition)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var competition = competitionRepository.findById(id).orElse(null)

            competition.updated_by = 1
            competition.updated = LocalDateTime.now()
            competition.deleted = true

            competitionRepository.save(competition)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
