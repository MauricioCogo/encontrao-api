package org.example.encontraoapi.controller

import org.example.encontraoapi.application.CampusApplication
import org.example.encontraoapi.entity.Campus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/campus")
class CampusController @Autowired constructor(
    private val campusApplication: CampusApplication
) {
    @GetMapping
    fun getAllCampus(): List<Campus> = campusApplication.getAll()

    @GetMapping("/{id}")
    fun getCampusById(@PathVariable id: Long): ResponseEntity<Campus> {
        val campus = campusApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(campus)
    }

    @PostMapping
    fun createCampus(@RequestBody campus: Campus): ResponseEntity<Campus> {
        val createdCampus = campusApplication.create(campus)
        return ResponseEntity.ok(createdCampus)
    }

    @PutMapping("/{id}")
    fun updateCampus(
        @PathVariable id: Long,
        @RequestBody campusDetails: Campus
    ): ResponseEntity<Campus> {
        val updatedCampus = campusApplication.update(id, campusDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedCampus)
    }

    @DeleteMapping("/{id}")
    fun deleteCampus(@PathVariable id: Long): ResponseEntity<Void> {
        return if (campusApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
