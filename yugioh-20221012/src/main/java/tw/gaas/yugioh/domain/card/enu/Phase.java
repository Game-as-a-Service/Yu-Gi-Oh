package tw.gaas.yugioh.domain.card.enu;

public enum Phase {

    // 初始化
    INIT,

    // 抽牌
    LEFT_DRAW,
    // 召喚怪獸
    LEFT_MONSTER,
    // 發動魔法卡
    LEFT_SPELL,
    // 覆蓋陷阱卡
    LEFT_TRAP,
    // 攻擊
    LEFT_BATTLE,

    // 抽牌
    RIGHT_DRAW,
    // 召喚怪獸
    RIGHT_MONSTER,
    // 發動魔法卡
    RIGHT_SPELL,
    // 覆蓋陷阱卡
    RIGHT_TRAP,
    // 攻擊
    RIGHT_BATTLE,
}
