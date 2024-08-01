package org.example.encontraoapi.controller

import org.example.encontraoapi.application.PresentationApplication
import org.example.encontraoapi.entity.Presentation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/presentations")
class PresentationController @Autowired constructor(
    private val presentationApplication: PresentationApplication
) {
    @GetMapping
    fun getAllPresentations(): List<Presentation> = presentationApplication.getAll()

    @GetMapping("/{id}")
    fun getPresentationById(@PathVariable id: Long): ResponseEntity<Presentation> {
        val presentation = presentationApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(presentation)
    }

    @PostMapping
    fun createPresentation(@RequestBody presentation: Presentation): ResponseEntity<Presentation> {
        val createdPresentation = presentationApplication.create(presentation)
        return ResponseEntity.ok(createdPresentation)
    }

    @PutMapping("/{id}")
    fun updatePresentation(
        @PathVariable id: Long,
        @RequestBody presentationDetails: Presentation
    ): ResponseEntity<Presentation> {
        val updatedPresentation = presentationApplication.update(id, presentationDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedPresentation)
    }

    @DeleteMapping("/{id}")
    fun deletePresentation(@PathVariable id: Long): ResponseEntity<Void> {
        return if (presentationApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
