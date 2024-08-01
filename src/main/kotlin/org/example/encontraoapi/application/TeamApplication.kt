package org.example.encontraoapi.application

import org.example.encontraoapi.entity.Team
import org.example.encontraoapi.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class TeamApplication @Autowired constructor(
    private val teamService: TeamService
) {

    fun getAll(): List<Team> {
        return teamService.getAll()
    }

    fun getById(id: Long): Team?{
        return teamService.getById(id)
    }

    fun create(team: Team): Team {
        try {
            if(team.grade!! > BigDecimal(10.00)){
                throw Exception("the grade is greater than 10")
            }

            if (team.grade!! < BigDecimal(00.00)){
                throw Exception("the grade is less than 0")
            }

            return teamService.create(team)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, teamDetails: Team): Team? {
        return teamService.update(id, teamDetails)
    }

    fun delete(id: Long): Boolean {
        return teamService.delete(id)
    }
}
