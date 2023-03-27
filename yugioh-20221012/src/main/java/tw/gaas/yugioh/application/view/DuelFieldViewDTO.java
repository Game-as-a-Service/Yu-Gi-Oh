package tw.gaas.yugioh.application.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.domain.enu.Phase;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldViewDTO {

    private List<String> boardMessages;

    private String uuid;

    private ZoneViewDTO left;

    private ZoneViewDTO right;

    private Phase phase;
}
