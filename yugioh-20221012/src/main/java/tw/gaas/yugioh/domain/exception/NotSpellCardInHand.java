package tw.gaas.yugioh.domain.exception;

public class NotSpellCardInHand extends RuntimeException {

    public NotSpellCardInHand(String message) {
        super(message);
    }
}
