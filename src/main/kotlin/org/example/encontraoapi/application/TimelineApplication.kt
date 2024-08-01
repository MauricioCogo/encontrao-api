package org.example.encontraoapi.application

import org.example.encontraoapi.entity.Timeline
import org.example.encontraoapi.service.TimelineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TimelineApplication @Autowired constructor(
    private val timelineService: TimelineService
) {

    fun getAll(): List<Timeline> {
        return timelineService.getAll()
    }

    fun getById(id: Long): Timeline?{
        return timelineService.getById(id)
    }

    fun create(timeline: Timeline): Timeline {
        try {
            if(timeline.name.isNullOrEmpty()){
                throw Exception("name can not be empty")
            }

            if(timeline.date == null){
                throw Exception("date can not be empty")
            }

            if(timeline.idPoint == null){
                throw Exception("id point can not be empty")
            }

            if(timeline.idCompetition == null){
                throw Exception("id competition can not be empty")
            }

            return timelineService.create(timeline)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, timelineDetails: Timeline): Timeline? {
        return timelineService.update(id, timelineDetails)
    }

    fun delete(id: Long): Boolean {
        return timelineService.delete(id)
    }
}
