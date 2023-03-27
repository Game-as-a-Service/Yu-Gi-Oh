package tw.gaas.yugioh.framework.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.gaas.yugioh.domain.exception.DuelFieldNotFound;
import tw.gaas.yugioh.domain.exception.NotCardInMonsterCards;
import tw.gaas.yugioh.domain.exception.NotDuelistInZone;
import tw.gaas.yugioh.domain.exception.NotMonsterCardInHand;
import tw.gaas.yugioh.domain.exception.NotSpellCardInHand;
import tw.gaas.yugioh.domain.exception.NotTrapCardInHand;
import tw.gaas.yugioh.domain.exception.WrongPhase;

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
