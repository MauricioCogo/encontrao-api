package org.example.encontraoapi.service

import org.example.encontraoapi.entity.Point
import org.example.encontraoapi.repository.PointRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PointService @Autowired constructor(
    private val pointRepository: PointRepository
) {
    fun getAll(): List<Point> {
        try {
            return pointRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Point? {
        try {
            return pointRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getByType(type: String): List<Point>? {
        try {
            return pointRepository.findByType(type)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(point: Point): Point {
        try {
            return pointRepository.save(point)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, pointDetails: Point): Point? {
        try {
            val point = pointRepository.findById(id).orElse(null) ?: return null

            val updatedPoint = Point().also {
                it.id = point.id
                it.name = pointDetails.name
                it.type = pointDetails.type
                it.description = pointDetails.description
                it.icon = pointDetails.icon
                it.latitude = pointDetails.latitude
                it.longitude = pointDetails.longitude
            }

            return pointRepository.save(updatedPoint)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var point = pointRepository.findById(id).orElse(null)

            point.updated_by = 1
            point.updated = LocalDateTime.now()
            point.deleted = true

            pointRepository.save(point)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
