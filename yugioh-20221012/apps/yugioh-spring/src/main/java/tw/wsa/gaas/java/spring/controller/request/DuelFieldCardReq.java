package tw.wsa.gaas.java.spring.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.wsa.gaas.java.domain.enu.CardState;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldCardReq {

    // 卡牌唯一識別碼
    private String uuid;
    // 卡牌表示方式
    private CardState cardState = CardState.FRONT;
}
