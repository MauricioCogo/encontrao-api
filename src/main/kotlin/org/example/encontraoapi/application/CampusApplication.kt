package org.example.encontraoapi.application

import org.example.encontraoapi.entity.Campus
import org.example.encontraoapi.service.CampusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CampusApplication @Autowired constructor(
    private val campusService: CampusService
) {

    fun getAll(): List<Campus> {
        return campusService.getAll()
    }

    fun getById(id: Long): Campus?{
        return campusService.getById(id)
    }

    fun create(campus: Campus): Campus {
        try {
            if(campus.institution.isNullOrEmpty()){
                throw Exception("institution can not be empty")
            }

            if(campus.coordinatorName.isNullOrEmpty()){
                throw Exception("coordinator name can not be empty")
            }

            if(campus.description.isNullOrEmpty()){
                throw Exception("description can not be empty")
            }

            if(campus.entity.isNullOrEmpty()){
                throw Exception("entity can not be empty")
            }

            if(campus.dormitory.isNullOrEmpty()){
                throw Exception("dormitory can not be empty")
            }

            if(campus.idPresentation == null){
                throw Exception("idPresentation can not be empty")
            }

            return campusService.create(campus)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, campusDetails: Campus): Campus? {
        return campusService.update(id, campusDetails)
    }

    fun delete(id: Long): Boolean {
        return campusService.delete(id)
    }
}
