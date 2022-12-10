package tw.gaas.yugioh.manager;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class PairManager {

    private final ConcurrentLinkedQueue<String> pairPool = new ConcurrentLinkedQueue<>();

    public Optional<String> requestPair() {
        return Optional.ofNullable(pairPool.poll());
    }

    public void submitPairRequest(String duelFieldUuid) {
        pairPool.offer(duelFieldUuid);
    }
}
