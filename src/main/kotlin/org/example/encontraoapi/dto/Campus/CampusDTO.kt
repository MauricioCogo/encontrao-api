// CampusDTO.kt
package org.example.encontraoapi.dto.Campus

import org.example.encontraoapi.entity.Campus

data class CampusDTO(
    var id: Long? = null,
    var institution: String? = null
) {
    constructor(data: Campus) : this(
        id = data.id,
        institution = data.institution
    )
}
