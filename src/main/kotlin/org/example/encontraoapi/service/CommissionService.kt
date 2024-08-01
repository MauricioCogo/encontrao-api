package org.example.encontraoapi.service

import org.example.encontraoapi.entity.Commission
import org.example.encontraoapi.repository.CommissionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommissionService @Autowired constructor(
    private val commissionRepository: CommissionRepository
) {
    fun getAll(): List<Commission> {
        try {
            return commissionRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): Commission? {
        try {
            return commissionRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(commission: Commission): Commission {
        try {
            return commissionRepository.save(commission)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, commissionDetails: Commission): Commission? {
        try {
            val commission = commissionRepository.findById(id).orElse(null) ?: return null

            val updatedCommission = Commission().also {
                it.id = commission.id
                it.idCompetitionsTeams = commissionDetails.idCompetitionsTeams
                it.idUser = commissionDetails.idUser
                it.grade1 = commissionDetails.grade1
                it.grade2 = commissionDetails.grade2
                it.grade3 = commissionDetails.grade3
                it.grade4 = commissionDetails.grade4
                it.grade5 = commissionDetails.grade5
                it.user = commissionDetails.user
                it.competitionsTeams =  commissionDetails.competitionsTeams
            }

            return commissionRepository.save(updatedCommission)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var commission = commissionRepository.findById(id).orElse(null)

            commission.updated_by = 1
            commission.updated = LocalDateTime.now()
            commission.deleted = true

            commissionRepository.save(commission)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
