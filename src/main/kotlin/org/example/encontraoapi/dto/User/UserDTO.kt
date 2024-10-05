package org.example.encontraoapi.dto.User

import org.example.encontraoapi.entity.User

interface MinifiedUserProjection {
    val id: Long?
    val name: String?
    val cpf: String?
    val avatar: String?
    val password: String?
    val evaluator: Boolean?
    val admin: Boolean?
    val roles: String?
    val campusId: Long?
    val registration: String?
    val campus: String?
}

data class UserDTO(
    var id: Long? = null,
    var name: String? = null,
    var avatar: String? = null,
    var password: String? = null,
    var cpf: String? = null,
    var registration: String? = null,
    var roles: String? = null,
    var isEvaluator: Boolean? = null,
    var isAdmin: Boolean? = null,
    var campusId: Long? = null,
    var campus: String? = null,
) {
    constructor(data: User) : this(
        id = data.id,
        name = data.name,
        password = data.password,
        avatar = data.avatar,
        cpf = data.cpf,
        registration = data.registration,
        roles = data.roles,
        isEvaluator = data.isEvaluator,
        isAdmin = data.isAdmin
    )

    fun toEntity(): User {
        return User().also {
            it.id = id
            it.name = name
            it.avatar = avatar
            it.cpf = cpf
            it.registration = registration
            it.roles = roles
            it.isEvaluator = isEvaluator ?: false
            it.isAdmin = isAdmin ?: false
        }
    }
}

fun MinifiedUserProjection.toDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        cpf = this.cpf,
        password = this.password,
        isEvaluator = this.evaluator,
        isAdmin = this.admin,
        roles = this.roles,
        campusId = this.campusId,
        registration = this.registration,
        campus = this.campus,
    )
}
