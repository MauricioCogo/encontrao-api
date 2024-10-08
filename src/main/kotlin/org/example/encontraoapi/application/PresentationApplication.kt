package org.example.encontraoapi.application

import org.example.encontraoapi.dto.Presentations.PresentationDTO
import org.example.encontraoapi.entity.Presentation
import org.example.encontraoapi.service.PresentationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PresentationApplication @Autowired constructor(
    private val presentationService: PresentationService
) {

    fun getAll(): List<Presentation> {
        return presentationService.getAll()
    }

    fun getById(id: Long): Presentation? {
        return presentationService.getById(id)
    }

    fun getAllWithCampus(): List<PresentationDTO>? {
        return presentationService.getAllWithCampus()
    }

    fun create(presentation: Presentation): Presentation {
        try {
            if (presentation.traditionalDance1.isNullOrEmpty()) {
                throw Exception("traditional dance 1 can not be empty")
            }

            return presentationService.create(presentation)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, presentationDetails: Presentation): Presentation? {
        return presentationService.update(id, presentationDetails)
    }

    fun delete(id: Long): Boolean {
        return presentationService.delete(id)
    }
}
