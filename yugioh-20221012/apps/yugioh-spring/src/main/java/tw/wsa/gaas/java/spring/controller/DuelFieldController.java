package tw.wsa.gaas.java.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.wsa.gaas.java.application.adapter.inport.command.DuelFieldCommand;
import tw.wsa.gaas.java.application.adapter.inport.query.DuelFieldQuery;
import tw.wsa.gaas.java.application.usecase.DuelFieldUseCase;
import tw.wsa.gaas.java.spring.config.security.JwtTokenService;
import tw.wsa.gaas.java.spring.config.security.UsernamePasswordPairDTO;
import tw.wsa.gaas.java.spring.controller.presenter.DuelFieldPresenter;
import tw.wsa.gaas.java.spring.controller.request.DuelFieldCardReq;
import tw.wsa.gaas.java.spring.controller.view.DuelFieldView;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
@RequiredArgsConstructor
public class DuelFieldController {

    private final AuthenticationManager authenticationManager;
    private final ConcurrentHashMap<String, List<SseEmitter>> duelFieldUuidAndSseEmitters = new ConcurrentHashMap<>();
    private final DuelFieldUseCase duelFieldUseCase;
    private final JwtTokenService jwtTokenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Operation(summary = "決鬥者登入")
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

    @Operation(summary = "查詢決鬥場")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelFields/{uuid}")
    public ResponseEntity<DuelFieldView> queryDuelField(@PathVariable String uuid) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
                DuelFieldQuery.builder().uuid(uuid).build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "查詢決鬥場SSE")
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

    @Operation(summary = "加入決鬥，自動配對")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields:join")
    public ResponseEntity<DuelFieldView> join(Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
                DuelFieldCommand
                        .join()
                        .duelistName(principal.getName())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "決鬥中，抽卡")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:drawCard")
    public ResponseEntity<DuelFieldView> drawCard(
            @PathVariable String uuid,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
                DuelFieldCommand
                        .drawCard()
                        .uuid(uuid)
                        .duelistName(principal.getName())
                        .build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @Operation(summary = "決鬥中，召喚怪獸")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:summonMonster")
    public ResponseEntity<DuelFieldView> summonMonster(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
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

    @Operation(summary = "決鬥中，使用魔法卡")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:applySpell")
    public ResponseEntity<DuelFieldView> applySpell(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
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

    @Operation(summary = "決鬥中，覆蓋陷阱卡")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:coverTrap")
    public ResponseEntity<DuelFieldView> coverTrap(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
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

    @Operation(summary = "決鬥中，開始戰鬥")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:startBattle")
    public ResponseEntity<DuelFieldView> startBattle(
            @PathVariable String uuid,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody DuelFieldCardReq duelFieldCardReq,
            Principal principal
    ) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
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

    @Scheduled(cron = "*/5 * * * * *")
    void broadcastDuelFieldsTask() {
        duelFieldUseCase
                .fetchAll()
                .stream()
                .collect(Collectors.groupingBy(duelField -> duelField.getEntityId().getUuid()))
                .forEach((uuid, duelFields) ->
                        Optional
                                .ofNullable(duelFieldUuidAndSseEmitters.get(uuid))
                                .ifPresent(emitters -> emitters
                                        .forEach(emitter -> {
                                            try {
                                                emitter.send(
                                                        SseEmitter
                                                                .event()
                                                                .id(OffsetDateTime.now().toString())
                                                                .name(String.format("Game:%s", uuid))
                                                                .data(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new DuelFieldView(duelFields.get(0))))
                                                );
                                            } catch (IOException ex) {
                                                log.error(ex.getMessage(), ex);
                                            }
                                        })
                                )
                );
    }
}
