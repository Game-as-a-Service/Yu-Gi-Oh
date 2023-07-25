package tw.wsa.gaas.java.spring.controller.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.wsa.gaas.java.domain.enu.Phase;
import tw.wsa.gaas.java.domain.vo.Zone;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldView {

    private String uuid;

    private Zone left;

    private Zone right;

    private Phase phase;
}
