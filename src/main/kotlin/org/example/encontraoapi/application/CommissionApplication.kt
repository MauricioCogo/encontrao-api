package org.example.encontraoapi.application

import org.example.encontraoapi.entity.Commission
import org.example.encontraoapi.service.CommissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CommissionApplication @Autowired constructor(
    private val commissionService: CommissionService
) {

    fun getAll(): List<Commission> {
        return commissionService.getAll()
    }

    fun getById(id: Long): Commission?{
        return commissionService.getById(id)
    }

    fun create(commission: Commission): Commission {
        try {

            if(commission.idCompetitionsTeams == null){
                throw Exception("id competitions teams can not be empty")
            }

            if(commission.idUser == null){
                throw Exception("id user name can not be empty")
            }

            if(commission.grade1 == null){
                throw Exception("description can not be empty")
            }

            if(commission.grade2 == null){
                throw Exception("entity can not be empty")
            }

            if(commission.grade3 == null){
                throw Exception("dormitory can not be empty")
            }

            if(commission.grade4 == null){
                throw Exception("idPresentation can not be empty")
            }

            validateGrade(commission)

            return commissionService.create(commission)
        } catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, commissionDetails: Commission): Commission? {
        return commissionService.update(id, commissionDetails)
    }

    fun delete(id: Long): Boolean {
        return commissionService.delete(id)
    }

    private fun validateGrade(comission: Commission){
        val max = BigDecimal(10.00)
        val min = BigDecimal(00.00)

        if(comission.grade1!! > max || comission.grade1!! < min) {
            throw Exception("grade one invalid")
        }

        if(comission.grade2!! > max || comission.grade2!! < min) {
            throw Exception("grade twe invalid")
        }

        if(comission.grade3!! > max || comission.grade3!! < min) {
            throw Exception("grade tree invalid")
        }

        if(comission.grade4!! > max || comission.grade4!! < min) {
            throw Exception("grade four invalid")
        }
    }
}
