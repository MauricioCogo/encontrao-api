import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TemplateController {

    @GetMapping("/")
    fun apresentacoes(): String {
        return "apresentacoes" // Renderiza o template 'apresentacoes.html'
    }

    @GetMapping("/")
    fun mapa(): String {
        return "mapa" // Renderiza o template 'mapa.html'
    }

    @GetMapping("/")
    fun cronograma(): String {
        return "cronograma" // Renderiza o template 'cronograma.html'
    }
}