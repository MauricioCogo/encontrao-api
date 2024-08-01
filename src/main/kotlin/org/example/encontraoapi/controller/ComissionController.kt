package org.example.encontraoapi.controller

import org.example.encontraoapi.application.CommissionApplication
import org.example.encontraoapi.entity.Commission
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/commissions")
class CommissionController @Autowired constructor(
    private val commissionApplication: CommissionApplication
) {
    @GetMapping
    fun getAllCommissions(): List<Commission> = commissionApplication.getAll()

    @GetMapping("/{id}")
    fun getCommissionById(@PathVariable id: Long): ResponseEntity<Commission> {
        val commission = commissionApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(commission)
    }

    @PostMapping
    fun cremateCommission(@RequestBody commission: Commission): ResponseEntity<Commission> {
        val crematedCommission = commissionApplication.create(commission)
        return ResponseEntity.ok(crematedCommission)
    }

    @PutMapping("/{id}")
    fun updmateCommission(
        @PathVariable id: Long,
        @RequestBody commissionDetails: Commission
    ): ResponseEntity<Commission> {
        val updatedCommission = commissionApplication.update(id, commissionDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedCommission)
    }

    @DeleteMapping("/{id}")
    fun deleteCommission(@PathVariable id: Long): ResponseEntity<Void> {
        return if (commissionApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
