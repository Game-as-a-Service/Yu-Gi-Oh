package tw.gaas.yugioh

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.CoreMatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PingStepDefinitions: CucumberTestContextConfiguration() {

    @Autowired
    lateinit var mockMvc: MockMvc
    private var resultActions: ResultActions? = null

    @Given("there is a ping API")
    fun i_have_a_ping_api() {
        resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get("/api/ping"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @When("I ping the ping API")
    fun i_ping_the_api() {
        resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get("/api/ping"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Then("I receive a pong response")
    fun i_receive_a_pong_response() {
        resultActions!!
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("pong")))
    }


}
