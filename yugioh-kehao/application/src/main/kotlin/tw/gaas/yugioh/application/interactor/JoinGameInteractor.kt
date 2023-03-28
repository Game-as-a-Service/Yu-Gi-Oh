package tw.gaas.yugioh.application.interactor

import tw.gaas.yugioh.application.Presenter
import tw.gaas.yugioh.application.gateway.GameGateway
import tw.gaas.yugioh.application.gateway.UserGateway
import tw.gaas.yugioh.application.model.req.JoinGameReq
import tw.gaas.yugioh.application.model.resp.FindGameResp
import tw.gaas.yugioh.application.model.resp.JoinGameResp
import tw.gaas.yugioh.domain.error.DemoException

class JoinGameInteractor(
    private val userGateway: UserGateway,
    private val gameGateway: GameGateway
) {
    fun join(joinGameReq: JoinGameReq, presenter: Presenter) {
        // 查詢 User
        val user = this.userGateway.find(joinGameReq.userId)
        if (user == null) {
            val error = DemoException("查無對應 ID 的使用者")
            val resp = FindGameResp(error = error)
            presenter.present(resp)
            return
        }

        // 尋找可加入 Game
        val game = this.gameGateway.findAvailableGame()
        // 觸發 Event
        val event = game.join(user)

        // 儲存 Game
        this.gameGateway.save(game)

        // 推送事件
        val resp = JoinGameResp(event, game)
        presenter.present(resp)
    }
}
