package tw.gaas.yugioh.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.data.enu.Phase;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldDto {

    private List<String> boardMessages;

    private String uuid;

    private ZoneDto left;

    private ZoneDto right;

    private Phase phase;
}
