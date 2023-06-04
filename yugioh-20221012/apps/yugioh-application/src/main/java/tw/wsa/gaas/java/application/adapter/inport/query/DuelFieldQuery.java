package tw.wsa.gaas.java.application.adapter.inport.query;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldQuery {

    private String uuid;
}
