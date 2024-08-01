package org.example.encontraoapi.controller

import org.example.encontraoapi.application.PointApplication
import org.example.encontraoapi.entity.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/points")
class PointController @Autowired constructor(
    private val pointApplication: PointApplication
) {
    @GetMapping
    fun getAllPoints(): List<Point> = pointApplication.getAll()

    @GetMapping("/{id}")
    fun getPointById(@PathVariable id: Long): ResponseEntity<Point> {
        val point = pointApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(point)
    }

    @PostMapping
    fun createPoint(@RequestBody point: Point): ResponseEntity<Point> {
        val createdPoint = pointApplication.create(point)
        return ResponseEntity.ok(createdPoint)
    }

    @PutMapping("/{id}")
    fun updatePoint(
        @PathVariable id: Long,
        @RequestBody pointDetails: Point
    ): ResponseEntity<Point> {
        val updatedPoint = pointApplication.update(id, pointDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedPoint)
    }

    @DeleteMapping("/{id}")
    fun deletePoint(@PathVariable id: Long): ResponseEntity<Void> {
        return if (pointApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
