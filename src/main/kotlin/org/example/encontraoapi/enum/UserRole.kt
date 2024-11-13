package org.example.encontraoapi.enum

enum class UserRole(val role: String) {
    ADMIN("admin"),
    USER("user");
    

    fun getValue(): String {
        return role
    }
}
