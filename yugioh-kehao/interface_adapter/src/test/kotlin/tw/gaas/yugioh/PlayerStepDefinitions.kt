package tw.gaas.yugioh

import io.cucumber.java.en.Given

class PlayerStepDefinitions {

    private var currentPlayer: String? = null
    @Given("there is a player")
    fun there_is_a_player() {
        currentPlayer = "player"
    }

    fun getCurrentPlayer(): String? {
        return currentPlayer
    }
}
