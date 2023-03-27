package tw.gaas.yugioh.domain.exception;

public class NotTrapCardInHand extends RuntimeException {

    public NotTrapCardInHand(String message) {
        super(message);
    }
}
