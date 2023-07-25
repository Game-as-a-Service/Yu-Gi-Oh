package tw.wsa.gaas.java.domain.vo.card;

import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.SpellType;

/**
 * 魔法卡
 */
public class SpellCard extends Card {

    // 魔法卡類型
    protected SpellType spellType;

    public SpellCard(
            String uuid,
            CardState cardState,
            String name,
            CardType cardType,
            String description,
            SpellType spellType) {
        super(uuid, cardState, name, cardType, description);
        this.spellType = spellType;
    }
}
