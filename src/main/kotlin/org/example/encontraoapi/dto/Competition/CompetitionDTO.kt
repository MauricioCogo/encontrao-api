package org.example.encontraoapi.dto.Competition

interface ParticipantsMinifiedProjection {
    val id: Long?
    val name: String?
    val roles: String?
    val registration: String?

}

data class ParticipantsDTO(
    var id: Long? = null,
    var name: String? = null,
    var registration: String? = null,
    var roles: String? = null,
)

fun ParticipantsMinifiedProjection.toDTO(): ParticipantsDTO {
    return ParticipantsDTO(
        id = this.id,
        name = this.name,
        roles = this.roles,
        registration = this.registration,

    )
}
