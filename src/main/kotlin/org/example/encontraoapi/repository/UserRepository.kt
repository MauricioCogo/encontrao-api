package org.example.encontraoapi.repository

import org.example.encontraoapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
<<<<<<< HEAD
interface UserRepository : JpaRepository<User, Long>{
    @Query("select u from User u where u.cpf = :cpf")
    fun findByDocument(cpf: String): User?
}
=======
interface UserRepository : JpaRepository<User, Long> 
>>>>>>> 506d5e9735f83d3b79b6f77c2ee9719ddca9e68f
