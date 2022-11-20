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

    public DuelField(String uuid, Phase phase) {
        this.uuid = uuid;
        this.phase = phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public DuelFieldDto toDto() {
        ZoneDto leftDto = null;
        if (left != null) {
            leftDto = left.toDto();
        }

        ZoneDto rightDto = null;
        if (right != null) {
            rightDto = right.toDto();
        }

        return DuelFieldDto
                .builder()
                .uuid(uuid)
                .left(leftDto)
                .right(rightDto)
                .phase(phase)
                .build();
    }
}
