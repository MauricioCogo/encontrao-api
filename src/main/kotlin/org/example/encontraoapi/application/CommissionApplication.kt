package org.example.encontraoapi.application

import org.example.encontraoapi.dto.Commission.CommissionDTO
import org.example.encontraoapi.dto.UpdateCommissionAndTeamRequest
import org.example.encontraoapi.entity.Commission
import org.example.encontraoapi.entity.Team
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

    fun getById(id: Long): Commission? {
        return commissionService.getById(id)
    }

    fun getByIdTeam(id: Long): CommissionDTO? {
        return commissionService.getByIdTeam(id)
    }

    fun create(commission: Commission): Commission {
        try {

            if (commission.idCompetitionsTeams == null) {
                throw Exception("id competitions teams can not be empty")
            }

            if (commission.idUser == null) {
                throw Exception("id user name can not be empty")
            }

            if (commission.grade1 == null) {
                throw Exception("description can not be empty")
            }

            if (commission.grade2 == null) {
                throw Exception("entity can not be empty")
            }

            if (commission.grade3 == null) {
                throw Exception("dormitory can not be empty")
            }

            if (commission.grade4 == null) {
                throw Exception("idPresentation can not be empty")
            }

            validateGrade(commission)

            return commissionService.create(commission)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, commissionDetails: Commission): Commission? {
        return commissionService.update(id, commissionDetails)
    }

    fun updateTeam(id: Long, request: UpdateCommissionAndTeamRequest): Pair<Commission?, Team?>? {
        return commissionService.updateByTeam(id, request)
    }

    fun delete(id: Long): Boolean {
        return commissionService.delete(id)
    }

    private fun validateGrade(commission: Commission) {
        val max = BigDecimal(10.00)
        val min = BigDecimal(0.00)

        // Validação para grade1
        if (commission.grade1 < min || commission.grade1 > max) {
            throw IllegalArgumentException("grade1 inválida. Deve estar entre $min e $max.")
        }

        // Validação para grade2
        if (commission.grade2 < min || commission.grade2 > max) {
            throw IllegalArgumentException("grade2 inválida. Deve estar entre $min e $max.")
        }

        // Validação para grade3
        if (commission.grade3 < min || commission.grade3 > max) {
            throw IllegalArgumentException("grade3 inválida. Deve estar entre $min e $max.")
        }

        // Validação para grade4
        if (commission.grade4 < min || commission.grade4 > max) {
            throw IllegalArgumentException("grade4 inválida. Deve estar entre $min e $max.")
        }

        // Validação para grade5, se não for nula
        if (commission.grade5 != null) {
            if (commission.grade5!! < min || commission.grade5!! > max) {
                throw IllegalArgumentException("grade5 inválida. Deve estar entre $min e $max.")
            }
        }
    }
}
