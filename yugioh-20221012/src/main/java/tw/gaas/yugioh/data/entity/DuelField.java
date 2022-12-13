package tw.gaas.yugioh.data.entity;

import lombok.ToString;
import net.purefunc.emoji.Emoji0;
import net.purefunc.emoji.Emoji2;
import net.purefunc.emoji.Emoji3;
import tw.gaas.yugioh.data.dto.DuelFieldDto;
import tw.gaas.yugioh.data.enu.Phase;
import tw.gaas.yugioh.data.enu.Side;
import tw.gaas.yugioh.data.enu.State;
import tw.gaas.yugioh.web.security.exception.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        boardMessages.add(String.format("%s Right Duelist %s Entered", Emoji2.MILITARY_MEDAL, right.getDuelist().getName()));
    }

    public void waitDuelist() {
        this.phase = Phase.INIT;
    }

    public void start() {
        left.start();
        right.start();
        this.phase = Phase.LEFT_DRAW;
        boardMessages.add(String.format("%s Game Start. Waiting for %s Draw", Emoji3.TRIANGULAR_FLAG, left.getDuelist().getName()));
    }

    public void validPhase(Phase phase) {
        if (this.phase != phase)
            throw new WrongPhase(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + this.phase.name() + " != " + phase.name());
    }

    public void validDuelist(Side side, String username) {
        if (side == Side.LEFT) {
            if (!left.validDuelist(username))
                throw new NotDuelistInZone(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + username);
        } else {
            if (!right.validDuelist(username))
                throw new NotDuelistInZone(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + username);
        }
    }

    public void validDuelistSummonMonster(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistMonsterHandCard(uuid))
                throw new NotMonsterCardInHand(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + "Not Monster in Hand " + uuid);
        } else {
            if (!right.validDuelistMonsterHandCard(uuid))
                throw new NotMonsterCardInHand(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + "Not Monster in Hand " + uuid);
        }
    }

    public void validDuelistApplySpell(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistSpellHandCard(uuid))
                throw new NotSpellCardInHand(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + "Not Spell in Hand " + uuid);
        } else {
            if (!right.validDuelistSpellHandCard(uuid))
                throw new NotSpellCardInHand(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + "Not Spell in Hand " + uuid);
        }
    }

    public void validDuelistCoverTrap(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistTrapHandCard(uuid))
                throw new NotTrapCardInHand(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + "Not Trap in Hand " + uuid);
        } else {
            if (!right.validDuelistTrapHandCard(uuid))
                throw new NotTrapCardInHand(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + "Not Trap in Hand " + uuid);
        }
    }

    public void validDuelistStartBattle(Side side, String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistMonsterCard(uuid))
                throw new NotCardInMonsterCards(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + uuid);
        } else {
            if (!right.validDuelistMonsterCard(uuid))
                throw new NotCardInMonsterCards(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + uuid);
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
        return side == Side.LEFT ? left.validDuelist(username) : right.validDuelist(username);
    }

    public DuelFieldDto toDto(Boolean isLeftDuelist, Boolean isRightDuelist) {
        return DuelFieldDto
                .builder()
                .boardMessages(boardMessages)
                .uuid(uuid)
                .left(Optional
                        .ofNullable(left)
                        .map(v -> v.toDto(isLeftDuelist))
                        .orElse(null)
                )
                .right(Optional
                        .ofNullable(right)
                        .map(v -> v.toDto(isRightDuelist))
                        .orElse(null)
                )
                .phase(phase)
                .build();
    }
}
