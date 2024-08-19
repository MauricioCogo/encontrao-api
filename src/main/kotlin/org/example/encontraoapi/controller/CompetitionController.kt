package org.example.encontraoapi.controller

import org.example.encontraoapi.application.CompetitionApplication
import org.example.encontraoapi.dto.Competition.ParticipantsDTO
import org.example.encontraoapi.entity.Competition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/competitions")
class CompetitionController @Autowired constructor(
    private val competitionApplication: CompetitionApplication
) {
    @GetMapping
    fun getAllCompetition(): List<Competition> = competitionApplication.getAll()

    @GetMapping("/{id}")
    fun getCompetitionById(@PathVariable id: Long): ResponseEntity<Competition> {
        val competition = competitionApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(competition)
    }

    @GetMapping("commission/")
    fun getCompetitionByCommission(): List<Competition> = competitionApplication.getCommission()

    @GetMapping("/participants/{id}")
    fun getUserByCompetitionId(@PathVariable id: Long): ResponseEntity<List<ParticipantsDTO>> {
        val participants = competitionApplication.getUserByCompetitionId(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(participants)
    }

    @PostMapping
    fun createCompetition(@RequestBody competition: Competition): ResponseEntity<Competition> {
        val createdCompetition = competitionApplication.create(competition)
        return ResponseEntity.ok(createdCompetition)
    }

    @PutMapping("/{id}")
    fun updateCompetition(
        @PathVariable id: Long,
        @RequestBody competitionDetails: Competition
    ): ResponseEntity<Competition> {
        val updatedCompetition = competitionApplication.update(id, competitionDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedCompetition)
    }

    @DeleteMapping("/{id}")
    fun deleteCompetition(@PathVariable id: Long): ResponseEntity<Void> {
        return if (competitionApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
