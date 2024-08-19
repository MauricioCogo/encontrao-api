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
        return commissionRepository.findAll()
    }

    fun getById(id: Long): Commission? {
        return commissionRepository.findById(id).orElse(null)
    }

    fun create(commission: Commission): Commission {
        return commissionRepository.save(commission)
    }

    fun update(id: Long, commissionDetails: Commission): Commission? {
        val commission = commissionRepository.findById(id).orElse(null) ?: return null

        // Atualizando diretamente as propriedades da entidade
        commission.apply {
            idCompetitionsTeams = commissionDetails.idCompetitionsTeams
            idUser = commissionDetails.idUser
            grade1 = commissionDetails.grade1
            grade2 = commissionDetails.grade2
            grade3 = commissionDetails.grade3
            grade4 = commissionDetails.grade4
            grade5 = commissionDetails.grade5
            nameGrade1 = commissionDetails.nameGrade1
            nameGrade2 = commissionDetails.nameGrade2
            nameGrade3 = commissionDetails.nameGrade3
            nameGrade4 = commissionDetails.nameGrade4
            nameGrade5 = commissionDetails.nameGrade5
            user = commissionDetails.user
            competitionsTeams = commissionDetails.competitionsTeams
        }

        return commissionRepository.save(commission)
    }

    fun delete(id: Long): Boolean {
        val commission = commissionRepository.findById(id).orElse(null) ?: return false

        commission.apply {
            updated_by = 1 // Id do usuário que realizou a atualização
            updated = LocalDateTime.now()
            deleted = true
        }

        commissionRepository.save(commission)
        return true
    }
}
