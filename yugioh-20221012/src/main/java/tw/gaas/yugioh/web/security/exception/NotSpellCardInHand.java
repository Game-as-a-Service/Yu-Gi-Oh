package tw.gaas.yugioh.web.security.exception;

public class NotSpellCardInHand extends RuntimeException {

    public NotSpellCardInHand(String message) {
        super(message);
    }
}
