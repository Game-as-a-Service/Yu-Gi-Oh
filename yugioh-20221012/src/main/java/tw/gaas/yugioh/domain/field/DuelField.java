package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.enu.Phase;
import tw.gaas.yugioh.domain.dto.DuelFieldDto;
import tw.gaas.yugioh.domain.dto.ZoneDto;

@ToString
public class DuelField {

    // UUID
    private String uuid;
    // 左邊場地
    private Zone left;
    // 右邊場地
    private Zone right;
    // 階段
    private Phase phase;

    public DuelField(String uuid, Zone left, Phase phase) {
        this.uuid = uuid;
        this.left = left;
        this.phase = phase;
    }

    public void setRight(Zone zone) {
        this.right = zone;
    }

    public void start() {
        left.setup();
        right.setup();
        this.phase = Phase.LEFT_DRAW;
    }

    public DuelFieldDto toDto() {
        ZoneDto rightDto = null;
        if (right != null) {
            rightDto = right.toDto();
        }

        return DuelFieldDto
                .builder()
                .uuid(uuid)
                .left(left.toDto())
                .right(rightDto)
                .phase(phase)
                .build();
    }
}
