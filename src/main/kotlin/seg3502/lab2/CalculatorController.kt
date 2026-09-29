package seg3502.lab2

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalculatorController {

    @RequestMapping("/")
    fun home(model: Model): String {
        model.addAttribute("first", "")
        model.addAttribute("second", "")
        model.addAttribute("result", "")
        model.addAttribute("error", "")
        return "home"
    }

    @GetMapping("/calculate")
    fun calculate(
        @RequestParam(defaultValue = "") first: String,
        @RequestParam(defaultValue = "") second: String,
        @RequestParam(defaultValue = "") operation: String,
        model: Model
    ): String {

        val a = first.toDoubleOrNull()
        val b = second.toDoubleOrNull()

        model.addAttribute("first", first)
        model.addAttribute("second", second)
        model.addAttribute("error", "")

        if (a == null || b == null) {
            model.addAttribute("error", "Saisissez deux nombres valides.")
            return "home"
        }

        // Vérifier la division par zéro avant de calculer.
        if (operation == "/" && b == 0.0) {
            model.addAttribute("error", "Division par zéro impossible.")
            return "home"
        }

        val result = when (operation) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> {
                model.addAttribute("error", "Opération inconnue.")
                return "home"
            }
        }

        model.addAttribute("result", result)

        return "home"
    }
}