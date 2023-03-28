package tw.gaas.yugioh.application.gateway

import tw.gaas.yugioh.domain.entity.User

interface UserGateway {
    // FIXME: 當前是直接使用 Entity 進行資料儲存，也許需要 Data Mapper 轉換
    fun find(id: String): User?
    fun save(user: User)
}
