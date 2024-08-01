package org.example.encontraoapi.service

import org.example.encontraoapi.entity.Campus
import org.example.encontraoapi.repository.CampusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CampusService @Autowired constructor(
    private val campusRepository: CampusRepository
) {
    fun getAll(): List<Campus> {
        try {
            return campusRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Campus? {
        try {
            return campusRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(campus: Campus): Campus {
        try {
            return campusRepository.save(campus)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, campusDetails: Campus): Campus? {
        try {
            val campus = campusRepository.findById(id).orElse(null) ?: return null

            val updatedCampus = Campus().also {
                it.id = campus.id
                it.institution = campusDetails.institution
                it.coordinatorName = campusDetails.coordinatorName
                it.description = campusDetails.description
                it.entity = campusDetails.entity
                it.dormitory = campusDetails.dormitory
                it.idPresentation = campusDetails.idPresentation
                it.presentation = campusDetails.presentation
            }

            return campusRepository.save(updatedCampus)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var campus = campusRepository.findById(id).orElse(null)

            campus.updated_by = 1
            campus.updated = LocalDateTime.now()
            campus.deleted = true

            campusRepository.save(campus)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
