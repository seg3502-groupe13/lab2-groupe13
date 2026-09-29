package seg3502.lab2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import kotlin.test.Test

@WebMvcTest(CalculatorController::class)
class CalculatorControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `2 plus 3 affiche 5`() {
        mockMvc.get("/calculate") {
            param("first", "2")
            param("second", "3")
            param("operation", "+")
        }
            .andExpect {
                status { isOk() }
                model { attribute("result", 5.0) }
                view { name("home") }
            }
    }

    @Test
    fun `10 moins 4 affiche 6`() {
        mockMvc.get("/calculate") {
            param("first", "10")
            param("second", "4")
            param("operation", "-")
        }
            .andExpect {
                status { isOk() }
                model { attribute("result", 6.0) }
                view { name("home") }
            }
    }

    @Test
    fun `3 fois 5 affiche 15`() {
        mockMvc.get("/calculate") {
            param("first", "3")
            param("second", "5")
            param("operation", "*")
        }
            .andExpect {
                status { isOk() }
                model { attribute("result", 15.0) }
                view { name("home") }
            }
    }

    @Test
    fun `10 divise par 4 affiche 2 point 5`() {
        mockMvc.get("/calculate") {
            param("first", "10")
            param("second", "4")
            param("operation", "/")
        }
            .andExpect {
                status { isOk() }
                model { attribute("result", 2.5) }
                view { name("home") }
            }
    }

    @Test
    fun `une valeur non numerique affiche une erreur`() {
        mockMvc.get("/calculate") {
            param("first", "abc")
            param("second", "3")
            param("operation", "+")
        }
            .andExpect {
                status { isOk() }
                model {
                    attribute(
                        "error",
                        "Saisissez deux nombres valides."
                    )
                }
                view { name("home") }
            }
    }

    @Test
    fun `division par zero affiche une erreur`() {
        mockMvc.get("/calculate") {
            param("first", "10")
            param("second", "0")
            param("operation", "/")
        }
            .andExpect {
                status { isOk() }
                model {
                    attribute(
                        "error",
                        "Division par zéro impossible."
                    )
                }
                view { name("home") }
            }
    }
}