package org.example.encontraoapi.service

import org.example.encontraoapi.dto.Presentations.PresentationDTO
import org.example.encontraoapi.dto.Presentations.toDTO
import org.example.encontraoapi.entity.Presentation
import org.example.encontraoapi.repository.PresentationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PresentationService @Autowired constructor(
    private val presentationRepository: PresentationRepository
) {
    fun getAll(): List<Presentation> {
        try {
            return presentationRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Presentation? {
        try {
            return presentationRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getAllWithCampus(): List<PresentationDTO>? {
        try {
            return presentationRepository.findAllWithCampus().map { it.toDTO() }
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(presentation: Presentation): Presentation {
        try {
            return presentationRepository.save(presentation)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, presentationDetails: Presentation): Presentation? {
        try {
            val presentation = presentationRepository.findById(id).orElse(null) ?: return null

            val updatedPresentation = Presentation().also {
                it.id = presentation.id
                it.order = presentationDetails.order
                it.entranceChoreography = presentationDetails.entranceChoreography
                it.traditionalDance1 = presentationDetails.traditionalDance1
                it.traditionalDance2 = presentationDetails.traditionalDance2
                it.traditionalDance3 = presentationDetails.traditionalDance3
                it.exitChoreography = presentationDetails.exitChoreography
                it.birivasDance1 = presentationDetails.birivasDance1
                it.birivasDance2 = presentationDetails.birivasDance2
                it.birivasDance3 = presentationDetails.birivasDance3
            }

            return presentationRepository.save(updatedPresentation)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var presentation = presentationRepository.findById(id).orElse(null)

            presentation.updated_by = 1
            presentation.updated = LocalDateTime.now()
            presentation.deleted = true

            presentationRepository.save(presentation)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
