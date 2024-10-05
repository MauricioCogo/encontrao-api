package org.example.encontraoapi.controller

import org.example.encontraoapi.application.CampusApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@RestController
@RequestMapping("/images")
class ImageController @Autowired constructor(
    private val campusApplication: CampusApplication
) {
    private val storageLocation = "src/main/resources/images"

    @GetMapping("/view/{fileName}")
    fun getImage(@PathVariable fileName: String): ResponseEntity<Resource> {
        val filePath: Path = Paths.get(storageLocation).resolve(fileName).normalize()
        val resource: Resource = UrlResource(filePath.toUri())

        if (!resource.exists()) {
            return ResponseEntity.notFound().build()
        }

        val contentType: String = Files.probeContentType(filePath) ?: "application/octet-stream"

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.filename + "\"")
            .body(resource)
    }

    @GetMapping("/view/icon/{fileName}")
    fun getIcon(@PathVariable fileName: String): ResponseEntity<Resource> {
        val filePath: Path = Paths.get(storageLocation + "/icons").resolve(fileName).normalize()
        val resource: Resource = UrlResource(filePath.toUri())

        if (!resource.exists()) {
            return ResponseEntity.notFound().build()
        }

        val contentType: String = Files.probeContentType(filePath) ?: "application/octet-stream"

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.filename + "\"")
            .body(resource)
    }
}
