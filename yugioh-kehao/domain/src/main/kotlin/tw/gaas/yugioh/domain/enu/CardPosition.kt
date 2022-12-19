package tw.gaas.yugioh.domain.enu

/**
 * 卡片位置
 * - FACE_UP: 表側表示
 * - FACE_DOWN：裡側表示
 * - NOT_PLACED：尚未放置於決鬥場
 */
enum class CardPosition(private val value: String) {
    FACE_UP("FACE_UP"),
    FACE_DOWN("FACE_DOWN"),
    NOT_PLACED("NOT_PLACED");
}
