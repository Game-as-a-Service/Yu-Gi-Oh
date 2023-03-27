package tw.gaas.yugioh.application.usecase.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.domain.enu.State;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardReqDTO {

    private String uuid;

    private State state = State.FRONT;
}
