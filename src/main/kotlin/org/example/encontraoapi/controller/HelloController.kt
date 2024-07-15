package org.example.encontraoapi.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("projectName", "encontrao-api")
        return "index"
    }
}
