package tw.gaas.yugioh

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.CoreMatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


class JoinGameStepDefinitions: CucumberTestContextConfiguration() {
    @Autowired
    lateinit var mockMvc: MockMvc

    var playerStepDefinitions: PlayerStepDefinitions = PlayerStepDefinitions()
    private var resultActions: ResultActions? = null

    @When("a player looking for a new game to join")
    @Throws(Exception::class)
    fun a_player_looking_for_a_new_game_to_join() {
        val player: String? = playerStepDefinitions.getCurrentPlayer()
        resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get("/player/$player"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Then("a player has joined a game")
    @Throws(Exception::class)
    fun a_player_has_joined_a_game() {
        resultActions!!
            .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("game")))
    }
}
