package tw.gaas.yugioh.domain.enu

/**
 * 階段
 * - WAITING: 對局等待 (需要其他玩家加入)
 * - READY: 對局已準備
 * - DRAW_PHASE: 抽牌階段
 * - MAIN_PHASE_1: 主要階段 1
 * - BATTLE_PHASE: 戰鬥階段
 * - END_PHASE: 結束階段
 * - FINISHED: 對局結束
 */
enum class Phase(private val value: String) {
    WAITING("WAITING"),
    READY("READY"),
    DRAW_PHASE("DRAW_PHASE"),
    MAIN_PHASE_1("MAIN_PHASE_1"),
    BATTLE_PHASE("BATTLE_PHASE"),
    END_PHASE("END_PHASE"),
    FINISHED("FINISHED");
}
