package org.example.encontraoapi.service

import org.example.encontraoapi.entity.Timeline
import org.example.encontraoapi.repository.TimelineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TimelineService @Autowired constructor(
    private val timelineRepository: TimelineRepository
) {
    fun getAll(): List<Timeline> {
        try {
            return timelineRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Timeline? {
        try {
            return timelineRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(timeline: Timeline): Timeline {
        try {
            return timelineRepository.save(timeline)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, timelineDetails: Timeline): Timeline? {
        try {
            val timeline = timelineRepository.findById(id).orElse(null) ?: return null

            val updatedTimeline = Timeline().also {
                it.id = timeline.id
                it.name = timelineDetails.name
                it.required = timelineDetails.required
                it.date = timelineDetails.date
                it.idPoint = timelineDetails.idPoint
                it.idCompetition = timelineDetails.idCompetition
                it.point = timelineDetails.point
                it.competition = timelineDetails.competition
            }

            return timelineRepository.save(updatedTimeline)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var timeline = timelineRepository.findById(id).orElse(null)

            timeline.updated_by = 1
            timeline.updated = LocalDateTime.now()
            timeline.deleted = true

            timelineRepository.save(timeline)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
