package tw.gaas.yugioh.framework.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tw.gaas.yugioh.application.usecase.DuelFieldUseCase;
import tw.gaas.yugioh.application.usecase.req.CardReqDTO;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
@RequiredArgsConstructor
public class DuelFieldController {

    private final DuelFieldUseCase duelFieldUseCase;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields:join")
    public ResponseEntity<String> joinDuelField(Principal principal) {



        return ResponseEntity.ok(duelFieldUseCase.join(principal.getName()));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:drawCard")
    public ResponseEntity<Boolean> duelistDrawCard(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            Principal principal
    ) {
        return ResponseEntity.ok(duelFieldUseCase.drawCard(uuid, principal.getName(), skip));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:summonMonster")
    public ResponseEntity<Boolean> duelistSummonMonster(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDTO cardReqDto,
            Principal principal) {
        return ResponseEntity.ok(duelFieldUseCase.summonMonster(uuid, principal.getName(), skip, cardReqDto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:applySpell")
    public ResponseEntity<Boolean> duelistApplySpell(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDTO cardReqDto,
            Principal principal
    ) {
        return ResponseEntity.ok(duelFieldUseCase.applySpell(uuid, principal.getName(), skip, cardReqDto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:coverTrap")
    public ResponseEntity<Boolean> duelistCoverTrap(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDTO cardReqDto,
            Principal principal
    ) {
        return ResponseEntity.ok(duelFieldUseCase.coverTrap(uuid, principal.getName(), skip, cardReqDto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:startBattle")
    public ResponseEntity<Boolean> duelistStartBattle(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDTO cardReqDto,
            Principal principal) {
        return ResponseEntity.ok(duelFieldUseCase.startBattle(uuid, principal.getName(), skip, cardReqDto));
    }



//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/duelField/{uuid}")
//    public DuelFieldViewDTO queryDuelField(
//            @PathVariable String uuid,
//            Principal principal) {
//
//        return findDuelFieldByUuid(uuid).toDto(
//                findDuelFieldByUuid(uuid).checkIsDuelist(Side.LEFT, principal.getName()),
//                findDuelFieldByUuid(uuid).checkIsDuelist(Side.RIGHT, principal.getName())
//        );
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/duelField/{uuid}:sse")
//    public SseEmitter listenDuelField(@PathVariable String uuid) throws IOException {
//        final SseEmitter sseEmitter = new SseEmitter(0L);
//        sseEmitter.send(
//                SseEmitter
//                        .event()
//                        .id(LocalDateTime.now().toString())
//                        .name("Connected, you're listening " + uuid + " duel field.")
//                        .reconnectTime(1000)
//        );
//
//        return networkManager.register(uuid, sseEmitter);
//    }
}
