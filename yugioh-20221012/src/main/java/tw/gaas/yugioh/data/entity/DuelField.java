package tw.gaas.yugioh.data.entity;

import lombok.ToString;
import net.purefunc.emoji.Emoji0;
import net.purefunc.emoji.Emoji2;
import net.purefunc.emoji.Emoji3;
import tw.gaas.yugioh.data.dto.DuelFieldDto;
import tw.gaas.yugioh.data.dto.ZoneDto;
import tw.gaas.yugioh.data.enu.Phase;
import tw.gaas.yugioh.data.enu.Side;
import tw.gaas.yugioh.data.enu.State;
import tw.gaas.yugioh.web.security.exception.NotCardInMonsterCards;
import tw.gaas.yugioh.web.security.exception.NotDuelistInZone;
import tw.gaas.yugioh.web.security.exception.NotMonsterCardInHand;
import tw.gaas.yugioh.web.security.exception.NotSpellCardInHand;
import tw.gaas.yugioh.web.security.exception.NotTrapCardInHand;
import tw.gaas.yugioh.web.security.exception.WrongPhase;

import java.util.LinkedList;
import java.util.List;

@ToString
public class DuelField {

    private List<String> boardMessages = new LinkedList<>();
    // UUID
    private String uuid;
    // 左邊場地
    private Zone left;
    // 右邊場地
    private Zone right;
    // 階段
    private Phase phase;
    private Boolean firstRound = true;

    public DuelField(String uuid) {
        this.uuid = uuid;
        boardMessages.add(String.format("%s DuelField %s Init", Emoji2.TROPHY, uuid));
    }

    public void setLeft(Zone left) {
        this.left = left;
        boardMessages.add(String.format("%s Left Duelist %s Entered", Emoji2.MILITARY_MEDAL, left.getDuelist().getName()));
    }

    public void setRight(Zone zone) {
        this.right = zone;
        boardMessages.add(String.format("%s Right Duelist %s Entered", Emoji2.MILITARY_MEDAL, left.getDuelist().getName()));
    }

    public void waitDuelist() {
        this.phase = Phase.INIT;
    }

    public void start() {
        left.setup();
        right.setup();
        this.phase = Phase.LEFT_DRAW;
        boardMessages.add(String.format("%s Game Start. Waiting for %s Draw", Emoji3.TRIANGULAR_FLAG, left.getDuelist().getName()));
    }

    public void validPhase(Phase phase) {
        if (this.phase != phase) throw new WrongPhase(this.phase.name() + " != " + phase.name());
    }

    public void validDuelist(Side side, String username) {
        if (side == Side.LEFT) {
            if (!left.validIsDuelist(username)) throw new NotDuelistInZone(username);
        } else {
            if (!right.validIsDuelist(username)) throw new NotDuelistInZone(username);
        }
    }

    public void duelistDraw(Side side) {
        if (side == Side.LEFT) {
            left.duelistDraw();
            this.phase = Phase.LEFT_MONSTER;
            boardMessages.add(String.format("%s Finished Draw. Waiting for %s Summon Monster", Emoji0.RAISED_BACK_OF_HAND, left.getDuelist().getName()));
        } else {
            right.duelistDraw();
            this.phase = Phase.RIGHT_MONSTER;
            boardMessages.add(String.format("%s Finished Draw. Waiting for %s Summon Monster", Emoji0.RAISED_BACK_OF_HAND, right.getDuelist().getName()));
        }
    }

    public void duelistSkipSummonMonster(Side side) {
        if (side == Side.LEFT) {
            this.phase = Phase.LEFT_SPELL;
            boardMessages.add(String.format("%s Skip Summon Monster. Waiting for %s Apply Spell", Emoji0.OGRE, left.getDuelist().getName()));
        } else {
            this.phase = Phase.RIGHT_SPELL;
            boardMessages.add(String.format("%s Skip Summon Monster. Waiting for %s Apply Spell", Emoji0.OGRE, right.getDuelist().getName()));
        }
    }

    public void validDuelistSummonMonster(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validIsDuelistMonsterHandCard(uuid))
                throw new NotMonsterCardInHand("Not Monster in Hand " + uuid);
        } else {
            if (!right.validIsDuelistMonsterHandCard(uuid))
                throw new NotMonsterCardInHand("Not Monster in Hand " + uuid);
        }
    }

    public void duelistSummonMonster(Side side, String uuid, State state) {
        if (side == Side.LEFT) {
            left.duelistSummonMonster(uuid, state);
            this.phase = Phase.LEFT_SPELL;
            boardMessages.add(String.format("%s Finished Summon Monster. Waiting for %s Apply Spell", Emoji0.OGRE, left.getDuelist().getName()));
        } else {
            right.duelistSummonMonster(uuid, state);
            this.phase = Phase.RIGHT_SPELL;
            boardMessages.add(String.format("%s Finished Summon Monster. Waiting for %s Apply Spell", Emoji0.OGRE, right.getDuelist().getName()));
        }
    }

    public void duelistSkipApplySpell(Side side) {
        if (side == Side.LEFT) {
            this.phase = Phase.LEFT_TRAP;
            boardMessages.add(String.format("%s Skip Apply Spell. Waiting for %s Cover Trap", Emoji0.DIZZY, left.getDuelist().getName()));
        } else {
            this.phase = Phase.RIGHT_TRAP;
            boardMessages.add(String.format("%s Skip Apply Spell. Waiting for %s Cover Trap", Emoji0.DIZZY, right.getDuelist().getName()));
        }
    }

    public void validDuelistApplySpell(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validIsDuelistSpellHandCard(uuid)) throw new NotSpellCardInHand("Not Spell in Hand " + uuid);
        } else {
            if (!right.validIsDuelistSpellHandCard(uuid)) throw new NotSpellCardInHand("Not Spell in Hand " + uuid);
        }
    }

    public void duelistApplySpell(Side side, String uuid) {
        if (side == Side.LEFT) {
            left.duelistApplySpell(uuid);
            this.phase = Phase.LEFT_TRAP;
            boardMessages.add(String.format("%s Finished Apply Spell. Waiting for %s Cover Trap", Emoji0.DIZZY, left.getDuelist().getName()));
        } else {
            right.duelistApplySpell(uuid);
            this.phase = Phase.RIGHT_TRAP;
            boardMessages.add(String.format("%s Finished Apply Spell. Waiting for %s Cover Trap", Emoji0.DIZZY, right.getDuelist().getName()));
        }
    }

    public void duelistSkipCoverTrap(Side side) {
        if (side == Side.LEFT) {
            this.phase = Phase.LEFT_BATTLE;
            boardMessages.add(String.format("%s Skip Cover Trap. Waiting for %s Battle", Emoji3.WHITE_LARGE_SQUARE, left.getDuelist().getName()));
        } else {
            this.phase = Phase.RIGHT_BATTLE;
            boardMessages.add(String.format("%s Skip Cover Trap. Waiting for %s Battle", Emoji3.WHITE_LARGE_SQUARE, right.getDuelist().getName()));
        }
    }

    public void validDuelistCoverTrap(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validIsDuelistTrapHandCard(uuid)) throw new NotTrapCardInHand("Not Trap in Hand " + uuid);
        } else {
            if (!right.validIsDuelistTrapHandCard(uuid)) throw new NotTrapCardInHand("Not Trap in Hand " + uuid);
        }
    }

    public void duelistCoverTrap(Side side, String uuid, State state) {
        if (side == Side.LEFT) {
            left.duelistCoverTrap(uuid, state);
            this.phase = Phase.LEFT_BATTLE;
            boardMessages.add(String.format("%s Finished Cover Trap. Waiting for %s Battle", Emoji3.WHITE_LARGE_SQUARE, left.getDuelist().getName()));
        } else {
            right.duelistCoverTrap(uuid, state);
            this.phase = Phase.RIGHT_BATTLE;
            boardMessages.add(String.format("%s Finished Cover Trap. Waiting for %s Battle", Emoji3.WHITE_LARGE_SQUARE, right.getDuelist().getName()));
        }
    }

    public void duelistSkipStartBattle(Side side) {
        if (side == Side.LEFT) {
            this.phase = Phase.RIGHT_DRAW;
            boardMessages.add(String.format("%s Skip Battle. Switch to %s", Emoji3.TRIDENT_EMBLEM, right.getDuelist().getName()));
        } else {
            this.phase = Phase.LEFT_DRAW;
            boardMessages.add(String.format("%s Skip Battle. Switch to %s", Emoji3.TRIDENT_EMBLEM, left.getDuelist().getName()));
        }
    }

    public void skipFirstRoundBattle() {
        if (firstRound) {
            this.firstRound = false;
            this.phase = Phase.RIGHT_DRAW;
            boardMessages.add(String.format("%s Skip First Round Battle. Switch to %s", Emoji3.TRIDENT_EMBLEM, right.getDuelist().getName()));
        }
    }

    public void validDuelistStartBattle(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validIsDuelistMonsterCard(uuid)) throw new NotCardInMonsterCards(uuid);
        } else {
            if (!right.validIsDuelistMonsterCard(uuid)) throw new NotCardInMonsterCards(uuid);
        }
    }

    public void duelistStartBattle(Side side, String uuid) {
        if (side == Side.LEFT) {
            left.duelistStartBattle(uuid, right);
            this.phase = Phase.RIGHT_DRAW;
            boardMessages.add(String.format("%s Finished Battle. Switch to %s", Emoji3.TRIDENT_EMBLEM, right.getDuelist().getName()));
        } else {
            right.duelistStartBattle(uuid, left);
            this.phase = Phase.LEFT_DRAW;
            boardMessages.add(String.format("%s Finished Battle. Switch to %s", Emoji3.TRIDENT_EMBLEM, left.getDuelist().getName()));
        }
    }

    public Boolean checkIsDuelist(Side side, String username) {
        return side == Side.LEFT ? left.validIsDuelist(username) : right.validIsDuelist(username);
    }

    public DuelFieldDto toDto(Boolean isLeftDuelist, Boolean isRightDuelist) {
        ZoneDto leftDto = null;
        if (left != null) {
            leftDto = left.toDto(isLeftDuelist);
        }

        ZoneDto rightDto = null;
        if (right != null) {
            rightDto = right.toDto(isRightDuelist);
        }

        return DuelFieldDto
                .builder()
                .boardMessages(boardMessages)
                .uuid(uuid)
                .left(leftDto)
                .right(rightDto)
                .phase(phase)
                .build();
    }
}