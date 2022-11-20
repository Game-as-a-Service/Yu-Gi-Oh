package tw.gaas.yugioh.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.domain.card.enu.Phase;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldDto {

    private String uuid;

    private ZoneDto left;

    private ZoneDto right;

    private Phase phase;
}
