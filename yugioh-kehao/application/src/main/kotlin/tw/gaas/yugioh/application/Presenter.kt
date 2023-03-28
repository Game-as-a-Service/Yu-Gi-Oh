package tw.gaas.yugioh.application

import tw.gaas.yugioh.application.model.resp.BasicResp

interface Presenter {
    fun present(resp: BasicResp)
}
