package tw.wsa.gaas.java.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import tw.wsa.gaas.java.application.adapter.inport.command.DuelFieldCommand;
import tw.wsa.gaas.java.application.adapter.inport.query.DuelFieldQuery;
import tw.wsa.gaas.java.application.usecase.DuelFieldUseCase;
import tw.wsa.gaas.java.domain.enu.CommandType;
import tw.wsa.gaas.java.spring.config.security.JwtTokenService;
import tw.wsa.gaas.java.spring.config.security.UsernamePasswordPairDTO;
import tw.wsa.gaas.java.spring.controller.presenter.DuelFieldPresenter;
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
public class GameController {

    private final AuthenticationManager authenticationManager;
    private final ConcurrentHashMap<String, List<SseEmitter>> duelFieldUuidAndSseEmitters = new ConcurrentHashMap<>();
    private final DuelFieldUseCase duelFieldUseCase;
    private final JwtTokenService jwtTokenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelField/{uuid}")
    public ResponseEntity<DuelFieldView> queryDuelField(@PathVariable String uuid, Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(
                DuelFieldQuery.builder().uuid(uuid).build(),
                duelFieldPresenter
        );

        return duelFieldPresenter.retrieveResponse();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelField/{uuid}:sse")
    public SseEmitter queryDuelFieldSse(@PathVariable String uuid) throws IOException {
        final SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.send(
                SseEmitter
                        .event()
                        .id(LocalDateTime.now().toString())
                        .name("Connected, you're listening " + uuid + " duel field.")
                        .reconnectTime(1000)
        );

        if (duelFieldUuidAndSseEmitters.containsKey(uuid)) duelFieldUuidAndSseEmitters.get(uuid).add(sseEmitter);
        else duelFieldUuidAndSseEmitters.put(uuid, List.of(sseEmitter));

        return sseEmitter;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields:join")
    public ResponseEntity<DuelFieldView> join(Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(DuelFieldCommand.builder().commandType(CommandType.JOIN).uuid("").duelistName(principal.getName()).build(), duelFieldPresenter);

        return duelFieldPresenter.retrieveResponse();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:drawCard")
    public ResponseEntity<DuelFieldView> drawCard(@PathVariable String uuid, Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(DuelFieldCommand.builder().commandType(CommandType.JOIN).uuid(uuid).duelistName(principal.getName()).build(), duelFieldPresenter);

        return duelFieldPresenter.retrieveResponse();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:summonMonster")
    public ResponseEntity<DuelFieldView> summonMonster(@PathVariable String uuid, Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(DuelFieldCommand.builder().commandType(CommandType.JOIN).uuid(uuid).duelistName(principal.getName()).build(), duelFieldPresenter);

        return duelFieldPresenter.retrieveResponse();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:applySpell")
    public ResponseEntity<DuelFieldView> applySpell(@PathVariable String uuid, Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(DuelFieldCommand.builder().commandType(CommandType.JOIN).uuid(uuid).duelistName(principal.getName()).build(), duelFieldPresenter);

        return duelFieldPresenter.retrieveResponse();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:coverTrap")
    public ResponseEntity<DuelFieldView> coverTrap(@PathVariable String uuid, Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(DuelFieldCommand.builder().commandType(CommandType.JOIN).uuid(uuid).duelistName(principal.getName()).build(), duelFieldPresenter);

        return duelFieldPresenter.retrieveResponse();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelFields/{uuid}:startBattle")
    public ResponseEntity<DuelFieldView> startBattle(@PathVariable String uuid, Principal principal) {
        DuelFieldPresenter duelFieldPresenter = new DuelFieldPresenter();
        duelFieldUseCase.execute(DuelFieldCommand.builder().commandType(CommandType.JOIN).uuid(uuid).duelistName(principal.getName()).build(), duelFieldPresenter);

        return duelFieldPresenter.retrieveResponse();
    }

//    @Scheduled(cron = "*/5 * * * * *")
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
