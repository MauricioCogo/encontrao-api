package org.example.encontraoapi.controller

import org.example.encontraoapi.application.TeamApplication
import org.example.encontraoapi.entity.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teams")
class TeamController @Autowired constructor(
    private val teamApplication: TeamApplication
) {
    @GetMapping
    fun getAllTeams(): List<Team> = teamApplication.getAll()

    @GetMapping("/{id}")
    fun getTeamById(@PathVariable id: Long): ResponseEntity<Team> {
        val team = teamApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(team)
    }

    @PostMapping
    fun createTeam(@RequestBody team: Team): ResponseEntity<Team> {
        val createdTeam = teamApplication.create(team)
        return ResponseEntity.ok(createdTeam)
    }

    @PutMapping("/{id}")
    fun updateTeam(
        @PathVariable id: Long,
        @RequestBody teamDetails: Team
    ): ResponseEntity<Team> {
        val updatedTeam = teamApplication.update(id, teamDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedTeam)
    }

    @DeleteMapping("/{id}")
    fun deleteTeam(@PathVariable id: Long): ResponseEntity<Void> {
        return if (teamApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
