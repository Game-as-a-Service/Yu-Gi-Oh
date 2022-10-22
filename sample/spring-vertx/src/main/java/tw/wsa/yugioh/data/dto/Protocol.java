package tw.wsa.yugioh.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.wsa.yugioh.data.enu.Action;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Protocol {

    private Action action;

    private Long userId;

    private String content;
}
