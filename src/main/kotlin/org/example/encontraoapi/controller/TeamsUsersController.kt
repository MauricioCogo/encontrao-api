package org.example.encontraoapi.controller

import org.example.encontraoapi.application.TeamsUsersApplication
import org.example.encontraoapi.entity.TeamsUsers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teams-users")
class TeamsTeamsUserssController @Autowired constructor(
    private val teamsUsersApplication: TeamsUsersApplication
) {
    @GetMapping
    fun getAllTeamsUserss(): List<TeamsUsers> = teamsUsersApplication.getAll()

    @GetMapping("/{id}")
    fun getTeamsUsersById(@PathVariable id: Long): ResponseEntity<TeamsUsers> {
        val teamsUsers = teamsUsersApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(teamsUsers)
    }

    @PostMapping
    fun createTeamsUsers(@RequestBody teamsUsers: TeamsUsers): ResponseEntity<TeamsUsers> {
        val createdTeamsUsers = teamsUsersApplication.create(teamsUsers)
        return ResponseEntity.ok(createdTeamsUsers)
    }

    @PutMapping("/{id}")
    fun updateTeamsUsers(
        @PathVariable id: Long,
        @RequestBody teamsUsersDetails: TeamsUsers
    ): ResponseEntity<TeamsUsers> {
        val updatedTeamsUsers = teamsUsersApplication.update(id, teamsUsersDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedTeamsUsers)
    }

    @DeleteMapping("/{id}")
    fun deleteTeamsUsers(@PathVariable id: Long): ResponseEntity<Void> {
        return if (teamsUsersApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
