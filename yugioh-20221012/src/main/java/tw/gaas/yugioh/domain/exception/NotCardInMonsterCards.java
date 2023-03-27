package tw.gaas.yugioh.domain.exception;

public class NotCardInMonsterCards extends RuntimeException {

    public NotCardInMonsterCards(String message) {
        super(message);
    }
}
