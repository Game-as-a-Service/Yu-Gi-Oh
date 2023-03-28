package tw.gaas.yugioh.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.data.enu.State;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardReqDto {

    private String uuid;

    private State state = State.FRONT;
}
