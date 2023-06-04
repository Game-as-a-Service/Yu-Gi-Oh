package tw.wsa.gaas.java.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.wsa.gaas.java.application.adaptar.inport.command.JoinDuelFieldCommand;
import tw.wsa.gaas.java.application.usecase.command.DuelFieldCommandUseCase;
import tw.wsa.gaas.java.application.usecase.query.DuelFieldQueryUseCase;
import tw.wsa.gaas.java.spring.config.security.JwtTokenService;
import tw.wsa.gaas.java.spring.config.security.UsernamePasswordPairDTO;
import tw.wsa.gaas.java.spring.controller.presenter.DuelFieldPresenter;
import tw.wsa.gaas.java.spring.controller.view.DuelFieldView;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
@RequiredArgsConstructor
public class GameController {

    private final AuthenticationManager authenticationManager;
    private final DuelFieldCommandUseCase duelFieldCommandUseCase;
    private final DuelFieldQueryUseCase duelFieldQueryUseCase;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/games:login")
    public ResponseEntity<String> login(@RequestBody UsernamePasswordPairDTO usernamePasswordPairDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usernamePasswordPairDTO.getUsername(),
                        usernamePasswordPairDTO.getPassword()
                )
        );

        final String jwt = jwtTokenService.generate(usernamePasswordPairDTO.getUsername(), UUID.randomUUID().toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", jwt))
                .body(jwt);
    }

    // POST /games:join
    // GET /games/{uuid}
    // GET /games/{uuid}/sse
    // POST /games/{uuid}:drawCard
    // POST /games/{uuid}:summonMonster
    // POST /games/{uuid}:applySpell
    // POST /games/{uuid}:coverTrap
    // POST /games/{uuid}:startBattle

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields:join")
    public ResponseEntity<DuelFieldView> joinDuelField(Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.joinDuelField(
                JoinDuelFieldCommand.builder().duelFieldName(principal.getName()).build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveDuelField();
    }

//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/duelFields/{uuid}:drawCard")
//    public ResponseEntity<Boolean> duelistDrawCard(
//            @PathVariable String uuid,
//            @RequestParam(required = false, defaultValue = "false") Boolean skip,
//            Principal principal
//    ) {
//        return ResponseEntity.ok(duelFieldUseCase.drawCard(uuid, principal.getName(), skip));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/duelFields/{uuid}:summonMonster")
//    public ResponseEntity<Boolean> duelistSummonMonster(
//            @PathVariable String uuid,
//            @RequestParam(required = false, defaultValue = "false") Boolean skip,
//            @RequestBody CardReqDTO cardReqDto,
//            Principal principal) {
//        return ResponseEntity.ok(duelFieldUseCase.summonMonster(uuid, principal.getName(), skip, cardReqDto));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/duelFields/{uuid}:applySpell")
//    public ResponseEntity<Boolean> duelistApplySpell(
//            @PathVariable String uuid,
//            @RequestParam(required = false, defaultValue = "false") Boolean skip,
//            @RequestBody CardReqDTO cardReqDto,
//            Principal principal
//    ) {
//        return ResponseEntity.ok(duelFieldUseCase.applySpell(uuid, principal.getName(), skip, cardReqDto));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/duelFields/{uuid}:coverTrap")
//    public ResponseEntity<Boolean> duelistCoverTrap(
//            @PathVariable String uuid,
//            @RequestParam(required = false, defaultValue = "false") Boolean skip,
//            @RequestBody CardReqDTO cardReqDto,
//            Principal principal
//    ) {
//        return ResponseEntity.ok(duelFieldUseCase.coverTrap(uuid, principal.getName(), skip, cardReqDto));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/duelFields/{uuid}:startBattle")
//    public ResponseEntity<Boolean> duelistStartBattle(
//            @PathVariable String uuid,
//            @RequestParam(required = false, defaultValue = "false") Boolean skip,
//            @RequestBody CardReqDTO cardReqDto,
//            Principal principal) {
//        return ResponseEntity.ok(duelFieldUseCase.startBattle(uuid, principal.getName(), skip, cardReqDto));
//    }
//
//
//
////    @PreAuthorize("hasRole('USER')")
////    @GetMapping("/duelField/{uuid}")
////    public DuelFieldViewDTO queryDuelField(
////            @PathVariable String uuid,
////            Principal principal) {
////
////        return findDuelFieldByUuid(uuid).toDto(
////                findDuelFieldByUuid(uuid).checkIsDuelist(Side.LEFT, principal.getName()),
////                findDuelFieldByUuid(uuid).checkIsDuelist(Side.RIGHT, principal.getName())
////        );
////    }
////
////    @PreAuthorize("hasRole('USER')")
////    @GetMapping("/duelField/{uuid}:sse")
////    public SseEmitter listenDuelField(@PathVariable String uuid) throws IOException {
////        final SseEmitter sseEmitter = new SseEmitter(0L);
////        sseEmitter.send(
////                SseEmitter
////                        .event()
////                        .id(LocalDateTime.now().toString())
////                        .name("Connected, you're listening " + uuid + " duel field.")
////                        .reconnectTime(1000)
////        );
////
////        return networkManager.register(uuid, sseEmitter);
////    }


}
