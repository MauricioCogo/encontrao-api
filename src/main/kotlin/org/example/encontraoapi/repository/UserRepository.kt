package org.example.encontraoapi.repository

import org.example.encontraoapi.dto.User.MinifiedUserProjection
import org.example.encontraoapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query(
        value =
        """
            SELECT 
                u.id AS id,
                u."name" AS name, 
                u.cpf AS cpf,
                u.registration AS registration,
                u.avatar AS avatar,
                u.password AS password,
                u.is_evaluator AS evaluator,
                u.is_admin AS admin,
                u.roles AS roles,
                c.id AS campusId,
                c.institution AS campus
            FROM users u 
            JOIN campus c ON u.id_campus = c.id 
            WHERE u.cpf = :cpf AND u.deleted = FALSE;
        """,
        nativeQuery = true
    )
    fun findUserWithCampusById(cpf: String): MinifiedUserProjection?

    @Query("select u from User u where u.cpf = :cpf")
    fun findByDocument(cpf: String): User?
}
