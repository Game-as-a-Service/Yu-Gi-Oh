package tw.wsa.gaas.java.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.wsa.gaas.java.application.adapter.inport.DuelFieldCommand;
import tw.wsa.gaas.java.application.usecase.DuelFieldCommandUseCase;
import tw.wsa.gaas.java.spring.config.security.JwtTokenService;
import tw.wsa.gaas.java.spring.config.security.UsernamePasswordPairDTO;
import tw.wsa.gaas.java.spring.controller.presenter.DuelFieldPresenter;
import tw.wsa.gaas.java.spring.controller.request.DuelFieldCardReq;
import tw.wsa.gaas.java.spring.controller.view.DuelFieldView;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Tag(name = "🂼 DuelField 決鬥場 🂼")
@RestController
@RequestMapping("/java/api/v1.0")
@RequiredArgsConstructor
public class DuelFieldController {

    private final AuthenticationManager authenticationManager;
    private final ConcurrentHashMap<String, List<SseEmitter>> duelFieldUuidAndSseEmitters = new ConcurrentHashMap<>();
    private final DuelFieldCommandUseCase duelFieldCommandUseCase;
    private final JwtTokenService jwtTokenService;

    @Operation(summary = "1.1 決鬥者登入")
    @PostMapping("/duelFields:login")
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

    @Operation(summary = "1.2 加入決鬥，自動配對")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields:join")
    public ResponseEntity<DuelFieldView> join(Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.execute(
                DuelFieldCommand
                        .join()
                        .uuid("JOIN")
                        .duelistName(principal.getName())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "1.3 建立決鬥場 Server Sent Event")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelFields/{uuid}:sse")
    public SseEmitter queryDuelFieldSse(@PathVariable String uuid) throws IOException {
        final SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.send(
                SseEmitter
                        .event()
                        .id(LocalDateTime.now().toString())
                        .name("Connected, you're listening " + uuid + " duel field.")
                        .reconnectTime(1000)
        );

        if (duelFieldUuidAndSseEmitters.containsKey(uuid)) {
            duelFieldUuidAndSseEmitters.get(uuid).add(sseEmitter);
        } else {
            duelFieldUuidAndSseEmitters.put(uuid, List.of(sseEmitter));
        }

        return sseEmitter;
    }

    @Operation(summary = "1.3 決鬥中，抽卡")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:drawCard")
    public ResponseEntity<DuelFieldView> drawCard(
            @PathVariable String uuid,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.execute(
                DuelFieldCommand
                        .drawCard()
                        .uuid(uuid)
                        .duelistName(principal.getName())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "1.4 決鬥中，召喚怪獸")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:summonMonster")
    public ResponseEntity<DuelFieldView> summonMonster(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.execute(
                DuelFieldCommand
                        .summonMonster()
                        .uuid(uuid)
                        .duelistName(principal.getName())
                        .skip(skip)
                        .cardUuid(duelFieldCardReq.getUuid())
                        .cardState(duelFieldCardReq.getCardState())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "1.5 決鬥中，使用魔法卡")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:applySpell")
    public ResponseEntity<DuelFieldView> applySpell(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.execute(
                DuelFieldCommand
                        .applySpell()
                        .uuid(uuid)
                        .duelistName(principal.getName())
                        .skip(skip)
                        .cardUuid(duelFieldCardReq.getUuid())
                        .cardState(duelFieldCardReq.getCardState())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "1.6 決鬥中，覆蓋陷阱卡")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:coverTrap")
    public ResponseEntity<DuelFieldView> coverTrap(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.execute(
                DuelFieldCommand
                        .coverTrap()
                        .uuid(uuid)
                        .duelistName(principal.getName())
                        .skip(skip)
                        .cardUuid(duelFieldCardReq.getUuid())
                        .cardState(duelFieldCardReq.getCardState())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "1.7 決鬥中，開始戰鬥")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:startBattle")
    public ResponseEntity<DuelFieldView> startBattle(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldCommandUseCase.execute(
                DuelFieldCommand
                        .startBattle()
                        .uuid(uuid)
                        .duelistName(principal.getName())
                        .skip(skip)
                        .cardUuid(duelFieldCardReq.getUuid())
                        .cardState(duelFieldCardReq.getCardState())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }



//    @Scheduled(cron = "*/5 * * * * *")
//    void broadcastDuelFieldsTask() {
//        duelFieldCommandUseCase
//                .fetchAll()
//                .stream()
//                .collect(Collectors.groupingBy(duelField -> duelField.getEntityId().getUuid()))
//                .forEach((uuid, duelFields) ->
//                        Optional
//                                .ofNullable(duelFieldUuidAndSseEmitters.get(uuid))
//                                .ifPresent(emitters -> emitters
//                                        .forEach(emitter -> {
//                                            try {
//                                                emitter.send(
//                                                        SseEmitter
//                                                                .event()
//                                                                .id(OffsetDateTime.now().toString())
//                                                                .name(String.format("Game:%s", uuid))
//                                                                .data(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new DuelFieldView(duelFields.get(0))))
//                                                );
//                                            } catch (IOException ex) {
//                                                log.error(ex.getMessage(), ex);
//                                            }
//                                        })
//                                )
//                );
//    }
}
