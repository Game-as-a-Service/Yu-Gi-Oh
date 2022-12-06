package tw.gaas.yugioh.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.gaas.yugioh.web.security.exception.DuelFieldNotFound;
import tw.gaas.yugioh.web.security.exception.NotCardInMonsterCards;
import tw.gaas.yugioh.web.security.exception.NotDuelistInZone;
import tw.gaas.yugioh.web.security.exception.NotMonsterCardInHand;
import tw.gaas.yugioh.web.security.exception.NotSpellCardInHand;
import tw.gaas.yugioh.web.security.exception.NotTrapCardInHand;
import tw.gaas.yugioh.web.security.exception.WrongPhase;

@Slf4j
@RestControllerAdvice
public class SpringControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalExceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ExceptionHandler(value = {
            DuelFieldNotFound.class,
            NotCardInMonsterCards.class,
            NotDuelistInZone.class,
            NotMonsterCardInHand.class,
            NotSpellCardInHand.class,
            NotTrapCardInHand.class,
            WrongPhase.class
    })
    public ResponseEntity<String> duelFieldNotFound(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
