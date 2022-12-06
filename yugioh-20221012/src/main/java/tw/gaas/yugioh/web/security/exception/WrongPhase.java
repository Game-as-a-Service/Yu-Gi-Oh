package tw.gaas.yugioh.web.security.exception;

public class WrongPhase extends RuntimeException {

    public WrongPhase(String message) {
        super(message);
    }
}
