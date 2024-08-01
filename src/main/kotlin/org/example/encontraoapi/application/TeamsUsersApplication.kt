package org.example.encontraoapi.application

import org.example.encontraoapi.entity.TeamsUsers
import org.example.encontraoapi.service.TeamsUsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TeamsUsersApplication @Autowired constructor(
    private val teamsUsersService: TeamsUsersService
) {

    fun getAll(): List<TeamsUsers> {
        return teamsUsersService.getAll()
    }

    fun getById(id: Long): TeamsUsers?{
        return teamsUsersService.getById(id)
    }

    fun create(teamsusers: TeamsUsers): TeamsUsers {
        try {
            if(teamsusers.idUser == null){
                throw Exception("id user can not be empty")
            }

            if(teamsusers.idTeam == null){
                throw Exception("id team name can not be empty")
            }

            return teamsUsersService.create(teamsusers)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, teamsusersDetails: TeamsUsers): TeamsUsers? {
        return teamsUsersService.update(id, teamsusersDetails)
    }

    fun delete(id: Long): Boolean {
        return teamsUsersService.delete(id)
    }
}
