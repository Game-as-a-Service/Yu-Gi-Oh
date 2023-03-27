package tw.gaas.yugioh.domain;

import lombok.ToString;
import tw.gaas.yugioh.domain.enu.Phase;
import tw.gaas.yugioh.domain.enu.Side;
import tw.gaas.yugioh.domain.enu.State;
import tw.gaas.yugioh.domain.exception.*;
import tw.gaas.yugioh.domain.vo.Zone;

@ToString
public class DuelField {

    // UUID
    private final String uuid;
    // 左邊場地
    private Zone left;
    // 右邊場地
    private Zone right;
    // 階段
    private Phase phase;
    // 是否為第一回合
    private Boolean firstRound = true;

    public DuelField(String uuid) {
        this.uuid = uuid;
    }

    public static DuelField create(String uuid) {
        return new DuelField(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public DuelFieldEvent prepareLeftZone(Zone left) {
        this.left = left;
        this.phase = Phase.INIT;

        return new DuelFieldEvent();
    }

    public DuelFieldEvent prepareRightZone(Zone zone) {
        this.right = zone;
        this.left.start();
        this.right.start();
        this.phase = Phase.LEFT_DRAW;

        return new DuelFieldEvent();
    }

    public DuelFieldEvent drawCard(
            Side side,
            String duelistName) {
        validPhase(side == Side.LEFT ? Phase.LEFT_DRAW : Phase.RIGHT_DRAW);
        validDuelist(side, duelistName);

        if (side == Side.LEFT) {
            left.duelistDraw();
            this.phase = Phase.LEFT_MONSTER;
        } else {
            right.duelistDraw();
            this.phase = Phase.RIGHT_MONSTER;
        }

        return new DuelFieldEvent();
    }

    public DuelFieldEvent summonMonster(
            Boolean skip,
            Side side,
            String uuid,
            State state,
            String duelistName) {
        validDuelistSummonMonster(side, uuid);
        validPhase(side == Side.LEFT ? Phase.LEFT_MONSTER : Phase.RIGHT_MONSTER);
        validDuelist(side, duelistName);

        if (side == Side.LEFT) {
            if (!skip) left.duelistSummonMonster(uuid, state);
            this.phase = Phase.LEFT_SPELL;
        } else {
            if (!skip) right.duelistSummonMonster(uuid, state);
            this.phase = Phase.RIGHT_SPELL;
        }

        return new DuelFieldEvent();
    }

    public DuelFieldEvent applySpell(
            Boolean skip,
            Side side,
            String uuid,
            State state,
            String duelistName) {
        validDuelistApplySpell(side, uuid);
        validPhase(side == Side.LEFT ? Phase.LEFT_SPELL : Phase.RIGHT_SPELL);
        validDuelist(side, duelistName);

        if (side == Side.LEFT) {
            if (!skip) left.duelistApplySpell(uuid);
            this.phase = Phase.LEFT_TRAP;
        } else {
            if (!skip) right.duelistApplySpell(uuid);
            this.phase = Phase.RIGHT_TRAP;
        }

        return new DuelFieldEvent();
    }

    public DuelFieldEvent coverTrap(
            Boolean skip,
            Side side,
            String uuid,
            State state,
            String duelistName) {
        validDuelistCoverTrap(side, uuid);
        validPhase(side == Side.LEFT ? Phase.LEFT_TRAP : Phase.RIGHT_TRAP);
        validDuelist(side, duelistName);

        if (side == Side.LEFT) {
            if (!skip) left.duelistCoverTrap(uuid, state);
            this.phase = Phase.LEFT_BATTLE;
        } else {
            if (!skip) right.duelistCoverTrap(uuid, state);
            this.phase = Phase.RIGHT_BATTLE;
        }

        return new DuelFieldEvent();
    }

    public DuelFieldEvent startBattle(
            Boolean skip,
            Side side,
            String uuid,
            String duelistName) {
        validDuelistStartBattle(side, uuid);
        validPhase(side == Side.LEFT ? Phase.LEFT_BATTLE : Phase.RIGHT_BATTLE);
        validDuelist(side, duelistName);

        if (side == Side.LEFT) {
            if (!firstRound && !skip) {
                firstRound = false;
                left.duelistStartBattle(uuid, right);
            }
            phase = Phase.RIGHT_DRAW;
        } else {
            if (!skip) right.duelistStartBattle(uuid, left);
            phase = Phase.LEFT_DRAW;
        }

        return new DuelFieldEvent();
    }

    private void validPhase(Phase phase) {
        if (this.phase != phase)
            throw new WrongPhase(phase);
    }

    private void validDuelist(
            Side side,
            String username) {
        if (side == Side.LEFT) {
            if (!left.validDuelist(username))
                throw new NotDuelistInZone(username);
        } else {
            if (!right.validDuelist(username))
                throw new NotDuelistInZone(username);
        }
    }

    private void validDuelistSummonMonster(
            Side side,
            String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistMonsterHandCard(uuid))
                throw new NotMonsterCardInHand(uuid);
        } else {
            if (!right.validDuelistMonsterHandCard(uuid))
                throw new NotMonsterCardInHand(uuid);
        }
    }

    private void validDuelistApplySpell(
            Side side,
            String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistSpellHandCard(uuid))
                throw new NotSpellCardInHand(uuid);
        } else {
            if (!right.validDuelistSpellHandCard(uuid))
                throw new NotSpellCardInHand(uuid);
        }
    }

    private void validDuelistCoverTrap(
            Side side,
            String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistTrapHandCard(uuid))
                throw new NotTrapCardInHand(uuid);
        } else {
            if (!right.validDuelistTrapHandCard(uuid))
                throw new NotTrapCardInHand(uuid);
        }
    }

    private void validDuelistStartBattle(
            Side side,
            String uuid) {
        if (side == Side.LEFT) {
            if (!left.validDuelistMonsterCard(uuid))
                throw new NotCardInMonsterCards(uuid);
        } else {
            if (!right.validDuelistMonsterCard(uuid))
                throw new NotCardInMonsterCards(uuid);
        }
    }
}
