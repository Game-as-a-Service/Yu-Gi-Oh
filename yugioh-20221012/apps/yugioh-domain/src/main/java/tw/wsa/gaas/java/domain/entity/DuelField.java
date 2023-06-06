package tw.wsa.gaas.java.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.Phase;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;
import tw.wsa.gaas.java.domain.vo.Zone;

import java.time.Instant;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class DuelField extends DomainEntity {

    // 左邊場地
    private Zone left;
    // 右邊場地
    private Zone right;
    // 階段
    private Phase phase;
    // 是否為第一回合
    private Boolean firstRound = true;

    private DuelField(String uuid, String duelistName) {
        this.entityId = EntityId
                .builder()
                .uuid(uuid)
                .createdBy(duelistName)
                .createdDate(Instant.now().toEpochMilli())
                .build();
    }

    public static DuelField create(String duelistName) {
        return new DuelField(UUID.randomUUID().toString(), duelistName);
    }

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

    public DuelFieldEvent drawCard(String duelistName) {
        if (duelistName.equals(left.getDuelist().getName())) {
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
            String cardUuid,
            CardState cardState,
            String duelistName) {
        if (duelistName.equals(left.getDuelist().getName())) {
            if (!skip) left.duelistSummonMonster(cardUuid, cardState);
            this.phase = Phase.LEFT_SPELL;
        } else {
            if (!skip) right.duelistSummonMonster(cardUuid, cardState);
            this.phase = Phase.RIGHT_SPELL;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent applySpell(
            Boolean skip,
            String cardUuid,
            String duelistName) {
        if (duelistName.equals(left.getDuelist().getName())) {
            if (!skip) left.duelistApplySpell(cardUuid);
            this.phase = Phase.LEFT_TRAP;
        } else {
            if (!skip) right.duelistApplySpell(cardUuid);
            this.phase = Phase.RIGHT_TRAP;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent coverTrap(
            Boolean skip,
            String cardUuid,
            CardState cardState,
            String duelistName) {
        if (duelistName.equals(left.getDuelist().getName())) {
            if (!skip) left.duelistCoverTrap(cardUuid, cardState);
            this.phase = Phase.LEFT_BATTLE;
        } else {
            if (!skip) right.duelistCoverTrap(cardUuid, cardState);
            this.phase = Phase.RIGHT_BATTLE;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }

    public DuelFieldEvent startBattle(
            Boolean skip,
            String cardUuid,
            String duelistName) {
        if (duelistName.equals(left.getDuelist().getName())) {
            if (!firstRound && !skip) {
                firstRound = false;
                left.duelistStartBattle(cardUuid, right);
            }
            phase = Phase.RIGHT_DRAW;
        } else {
            if (!skip) right.duelistStartBattle(cardUuid, left);
            phase = Phase.LEFT_DRAW;
        }

        return DuelFieldEvent
                .builder()
                .entityId(entityId)
                .build();
    }
}
