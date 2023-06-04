package tw.wsa.gaas.java.application.adapter.inport.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.wsa.gaas.java.domain.enu.CommandType;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldCommand {

    private CommandType commandType;
    private String uuid;
    private String duelistName;
}
