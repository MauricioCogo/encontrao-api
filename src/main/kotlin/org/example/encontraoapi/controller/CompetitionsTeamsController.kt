package org.example.encontraoapi.controller

import FecultDTO
import UserDetailsDTO
import org.example.encontraoapi.application.CompetitionsTeamsApplication
import org.example.encontraoapi.entity.CompetitionsTeams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/competitions-teams")
class CompetitionsTeamsController @Autowired constructor(
    private val competitionsTeamsApplication: CompetitionsTeamsApplication
) {
    @GetMapping
    fun getAllCompetitionsTeams(): List<CompetitionsTeams> = competitionsTeamsApplication.getAll()

    @GetMapping("/{id}")
    fun getCompetitionsTeamsById(@PathVariable id: Long): ResponseEntity<CompetitionsTeams> {
        val competitionsTeams = competitionsTeamsApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(competitionsTeams)
    }

    @GetMapping("competition/{id}")
    fun getCompetitionsTeamByCompetitionID(@PathVariable id: Long): ResponseEntity<List<UserDetailsDTO>> {
        val competitionsTeams =
            competitionsTeamsApplication.getByCompetition(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(competitionsTeams)
    }

    @GetMapping("participants/{id}")
    fun getCompetitionsTeamByCompetition(@PathVariable id: Long): ResponseEntity<List<UserDetailsDTO>> {
        val competitionsTeams =
            competitionsTeamsApplication.getAllParticipantsByCompetition(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(competitionsTeams)
    }

    @GetMapping("fecult")
    fun getCompetitionsTeamByFecult(): ResponseEntity<List<FecultDTO>> {
        val competitionsTeams =
            competitionsTeamsApplication.getByFecult() ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(competitionsTeams)
    }

    @PostMapping
    fun createCompetitionsTeams(@RequestBody competitionsTeams: CompetitionsTeams): ResponseEntity<CompetitionsTeams> {
        val createdCompetitionsTeams = competitionsTeamsApplication.create(competitionsTeams)
        return ResponseEntity.ok(createdCompetitionsTeams)
    }

    @PutMapping("/{id}")
    fun updateCompetitionsTeams(
        @PathVariable id: Long,
        @RequestBody competitionsTeamsDetails: CompetitionsTeams
    ): ResponseEntity<CompetitionsTeams> {
        val updatedCompetitionsTeams =
            competitionsTeamsApplication.update(id, competitionsTeamsDetails) ?: return ResponseEntity.notFound()
                .build()
        return ResponseEntity.ok(updatedCompetitionsTeams)
    }

    @DeleteMapping("/{id}")
    fun deleteCompetitionsTeams(@PathVariable id: Long): ResponseEntity<Void> {
        return if (competitionsTeamsApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
