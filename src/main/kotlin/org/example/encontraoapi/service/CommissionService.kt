package org.example.encontraoapi.service

import org.example.encontraoapi.dto.Commission.CommissionDTO
import org.example.encontraoapi.dto.Commission.CommissionGradeTeamDTO
import org.example.encontraoapi.dto.Commission.toDTO
import org.example.encontraoapi.dto.UpdateCommissionAndTeamRequest
import org.example.encontraoapi.entity.Commission
import org.example.encontraoapi.entity.Team
import org.example.encontraoapi.repository.CommissionRepository
import org.example.encontraoapi.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommissionService @Autowired constructor(
    private val commissionRepository: CommissionRepository,
    private val teamRepository: TeamRepository
) {
    fun getAll(): List<Commission> {
        return commissionRepository.findAll()
    }

    fun getById(id: Long): Commission? {
        return commissionRepository.findById(id).orElse(null)
    }

    fun getByIdTeam(id: Long): CommissionDTO? {
        return commissionRepository.findByIdTeam(id).toDTO()
    }

    fun getGradeByIdTeam(id: Long): List<CommissionGradeTeamDTO>? {
        return commissionRepository.findGradeByIdTeam(id).map { it.toDTO() }
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

    fun updateByTeam(id: Long, details: UpdateCommissionAndTeamRequest): Pair<Commission?, Team?>? {
        val commission = commissionRepository.findById(id).orElse(null) ?: return Pair(null, null)

        // Atualizando diretamente as propriedades da entidade
        commission.apply {
            idCompetitionsTeams = details.commission.idCompetitionsTeams
            idUser = details.commission.idUser
            grade1 = details.commission.grade1
            grade2 = details.commission.grade2
            grade3 = details.commission.grade3
            grade4 = details.commission.grade4
            grade5 = details.commission.grade5
        }

        val updatedCommission = commissionRepository.save(commission)

        val team =
            details.team.id?.let { teamRepository.findById(it).orElse(null) } ?: return Pair(updatedCommission, null)
        team.apply {
            grade = details.team.grade
        }

        val updatedTeam = teamRepository.save(team)

        return Pair(updatedCommission, updatedTeam)
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
