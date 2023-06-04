package tw.wsa.gaas.java.domain.entity;

import lombok.*;
import tw.wsa.gaas.java.domain.enu.Phase;
import tw.wsa.gaas.java.domain.enu.Side;
import tw.wsa.gaas.java.domain.enu.State;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;
import tw.wsa.gaas.java.domain.vo.Zone;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DuelField extends DomainEntity {

    // 左邊場地
    private Zone left;
    // 右邊場地
    private Zone right;
    // 階段
    private Phase phase;
    // 是否為第一回合
    private Boolean firstRound = true;

    public DuelFieldEvent prepareLeftZone(Zone left) {
        this.left = left;
        this.phase = Phase.INIT;

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent prepareRightZone(Zone zone) {
        this.right = zone;
        this.left.start();
        this.right.start();
        this.phase = Phase.LEFT_DRAW;

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent drawCard(
            Side side,
            String duelistName) {
        if (side == Side.LEFT) {
            left.duelistDraw();
            this.phase = Phase.LEFT_MONSTER;
        } else {
            right.duelistDraw();
            this.phase = Phase.RIGHT_MONSTER;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent summonMonster(
            Boolean skip,
            Side side,
            String uuid,
            State state,
            String duelistName) {
        if (side == Side.LEFT) {
            if (!skip) left.duelistSummonMonster(uuid, state);
            this.phase = Phase.LEFT_SPELL;
        } else {
            if (!skip) right.duelistSummonMonster(uuid, state);
            this.phase = Phase.RIGHT_SPELL;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent applySpell(
            Boolean skip,
            Side side,
            String uuid,
            State state,
            String duelistName) {
        if (side == Side.LEFT) {
            if (!skip) left.duelistApplySpell(uuid);
            this.phase = Phase.LEFT_TRAP;
        } else {
            if (!skip) right.duelistApplySpell(uuid);
            this.phase = Phase.RIGHT_TRAP;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent coverTrap(
            Boolean skip,
            Side side,
            String uuid,
            State state,
            String duelistName) {
        if (side == Side.LEFT) {
            if (!skip) left.duelistCoverTrap(uuid, state);
            this.phase = Phase.LEFT_BATTLE;
        } else {
            if (!skip) right.duelistCoverTrap(uuid, state);
            this.phase = Phase.RIGHT_BATTLE;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent startBattle(
            Boolean skip,
            Side side,
            String uuid,
            String duelistName) {
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

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }
}
