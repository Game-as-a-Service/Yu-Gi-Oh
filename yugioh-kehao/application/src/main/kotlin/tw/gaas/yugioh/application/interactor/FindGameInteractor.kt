package tw.gaas.yugioh.application.interactor

import tw.gaas.yugioh.application.Presenter
import tw.gaas.yugioh.application.gateway.GameGateway
import tw.gaas.yugioh.application.gateway.UserGateway
import tw.gaas.yugioh.application.model.req.FindGameReq
import tw.gaas.yugioh.application.model.resp.JoinGameResp
import tw.gaas.yugioh.domain.error.DemoException

class FindGameInteractor(
    private val userGateway: UserGateway,
    private val gameGateway: GameGateway
) {
    public fun find(req: FindGameReq, presenter: Presenter) {
        // 查詢指定 ID 遊戲
        val game = this.gameGateway.find(req.gameId)
        if (game == null) {
            val error = DemoException("查無指定 ID 的遊戲")
            val resp = JoinGameResp(error = error)
            presenter.present(resp)
            return
        }

        // 推送事件
        val resp = JoinGameResp(game = game)
        presenter.present(resp)
    }
}
