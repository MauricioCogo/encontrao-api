package org.example.encontraoapi.controller

import org.example.encontraoapi.application.TimelineApplication
import org.example.encontraoapi.dto.Timeline.TimelineDTO
import org.example.encontraoapi.entity.Timeline
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timeline")
class TimelineController @Autowired constructor(
    private val timelineApplication: TimelineApplication
) {
    @GetMapping
    fun getAllTimelines(): List<Timeline> = timelineApplication.getAll()

    @GetMapping("/{id}")
    fun getTimelineById(@PathVariable id: Long): ResponseEntity<Timeline> {
        val timeline = timelineApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(timeline)
    }

    @GetMapping("user/{id}")
    fun getTimelineByUserId(@PathVariable id: Long): ResponseEntity<List<TimelineDTO>> {
        val timeline = timelineApplication.getAllByIdUser(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(timeline)
    }

    @PostMapping
    fun createTimeline(@RequestBody timeline: Timeline): ResponseEntity<Timeline> {
        val createdTimeline = timelineApplication.create(timeline)
        return ResponseEntity.ok(createdTimeline)
    }

    @PutMapping("/{id}")
    fun updateTimeline(
        @PathVariable id: Long,
        @RequestBody timelineDetails: Timeline
    ): ResponseEntity<Timeline> {
        val updatedTimeline =
            timelineApplication.update(id, timelineDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedTimeline)
    }

    @DeleteMapping("/{id}")
    fun deleteTimeline(@PathVariable id: Long): ResponseEntity<Void> {
        return if (timelineApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
