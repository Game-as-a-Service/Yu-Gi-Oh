package tw.gaas.yugioh.web.security.exception;

public class NotCardInMonsterCards extends RuntimeException {

    public NotCardInMonsterCards(String message) {
        super(message);
    }
}
