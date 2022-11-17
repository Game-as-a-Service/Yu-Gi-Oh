package tw.gaas.yugioh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelField {

    // 左邊場地
    private Zone left;
    // 右邊場地
    private Zone right;
}
