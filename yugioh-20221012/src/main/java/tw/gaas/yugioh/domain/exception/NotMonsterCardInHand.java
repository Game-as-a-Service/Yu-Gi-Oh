package tw.gaas.yugioh.domain.exception;

public class NotMonsterCardInHand extends RuntimeException {

    public NotMonsterCardInHand(String message) {
        super(message);
    }
}
