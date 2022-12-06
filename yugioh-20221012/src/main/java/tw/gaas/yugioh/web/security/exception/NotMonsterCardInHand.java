package tw.gaas.yugioh.web.security.exception;

public class NotMonsterCardInHand extends RuntimeException {

    public NotMonsterCardInHand(String message) {
        super(message);
    }
}
