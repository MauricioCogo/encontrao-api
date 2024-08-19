package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    @Query("select u from User u where u.cpf = :cpf")
    fun findByDocument(cpf: String): User?
}