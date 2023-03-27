package tw.gaas.yugioh.domain.exception;

import tw.gaas.yugioh.domain.enu.Phase;

public class WrongPhase extends RuntimeException {

    public WrongPhase(Phase phase) {
        super(phase.name());
    }
}
