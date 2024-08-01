package org.example.encontraoapi.application

import org.example.encontraoapi.entity.Point
import org.example.encontraoapi.service.PointService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PointApplication @Autowired constructor(
    private val pointService: PointService
) {

    fun getAll(): List<Point> {
        return pointService.getAll()
    }

    fun getById(id: Long): Point?{
        return pointService.getById(id)
    }

    fun create(point: Point): Point {
        try {
            if(point.name.isNullOrEmpty()){
                throw Exception("name can not be empty")
            }

            if(point.type.isNullOrEmpty()){
                throw Exception("type can not be empty")
            }

            if(point.description.isNullOrEmpty()){
                throw Exception("description can not be empty")
            }

            if(point.description.isNullOrEmpty()){
                throw Exception("description can not be empty")
            }

            if(point.icon.isNullOrEmpty()){
                throw Exception("icon can not be empty")
            }

            if(point.latitude == null){
                throw Exception("latitude can not be empty")
            }

            if(point.longitude == null){
                throw Exception("longitude can not be empty")
            }

            return pointService.create(point)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, pointDetails: Point): Point? {
        return pointService.update(id, pointDetails)
    }

    fun delete(id: Long): Boolean {
        return pointService.delete(id)
    }
}
