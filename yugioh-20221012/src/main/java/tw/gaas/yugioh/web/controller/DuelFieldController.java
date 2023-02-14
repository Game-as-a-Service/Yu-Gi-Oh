package tw.gaas.yugioh.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.purefunc.emoji.Emoji2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.gaas.yugioh.data.dto.CardReqDto;
import tw.gaas.yugioh.data.dto.DuelFieldDto;
import tw.gaas.yugioh.data.entity.DuelField;
import tw.gaas.yugioh.data.entity.Duelist;
import tw.gaas.yugioh.data.entity.Zone;
import tw.gaas.yugioh.data.enu.Side;
import tw.gaas.yugioh.manager.GameManager;
import tw.gaas.yugioh.manager.NetworkManager;
import tw.gaas.yugioh.manager.PairManager;
import tw.gaas.yugioh.web.security.exception.DuelFieldNotFound;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
public class DuelFieldController {

    private final GameManager gameManager;
    private final PairManager pairManager;
    private final NetworkManager networkManager;

    public DuelFieldController(
            GameManager gameManager,
            PairManager pairManager,
            NetworkManager networkManager
    ) {
        this.gameManager = gameManager;
        this.pairManager = pairManager;
        this.networkManager = networkManager;
    }

    @GetMapping("/status")
    public String getStatus() {
        return String.format("Hello Duelist! %s%s%s", Emoji2.MILITARY_MEDAL, Emoji2.MILITARY_MEDAL, Emoji2.MILITARY_MEDAL);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField:join")
    public Map<String, String> joinDuelField(Principal principal) {
        final Duelist duelist = new Duelist(principal.getName());
        final Zone zone = new Zone(duelist);

        return pairManager
                .requestPair()
                .map(duelFieldUuid -> {
                    final DuelField duelField = gameManager.findDuelFieldByUuid(duelFieldUuid);
                    duelField.initRight(zone);
                    duelField.start();

                    return Map.of("duelFieldUuid", duelFieldUuid);
                })
                .orElseGet(() -> {
                    final String duelFieldUuid = UUID.randomUUID().toString();
                    final DuelField duelField = new DuelField(duelFieldUuid);
                    duelField.initLeft(zone);
                    duelField.waitRight();

                    pairManager.submitPairRequest(duelFieldUuid);
                    gameManager.putDuelFieldWithUuid(duelFieldUuid, duelField);

                    return Map.of("duelFieldUuid", duelFieldUuid);
                });
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:drawCard")
    public void duelistDrawCard(
            @PathVariable String uuid,
            @PathVariable Side side,
            Principal principal) {
        findDuelFieldByUuid(uuid).duelistDraw(side, principal.getName());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:summonMonster")
    public void duelistSummonMonster(
            @PathVariable String uuid,
            @PathVariable Side side,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDto cardReqDto,
            Principal principal) {
        if (skip) {
            findDuelFieldByUuid(uuid).duelistSkipSummonMonster(side);
        } else {
            findDuelFieldByUuid(uuid).duelistSummonMonster(side, cardReqDto.getUuid(), cardReqDto.getState(), principal.getName());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:applySpell")
    public void duelistApplySpell(
            @PathVariable String uuid,
            @PathVariable Side side,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDto cardReqDto, Principal principal) {
        if (skip) {
            findDuelFieldByUuid(uuid).duelistSkipApplySpell(side);
        } else {
            findDuelFieldByUuid(uuid).duelistApplySpell(side, cardReqDto.getUuid(), principal.getName());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:coverTrap")
    public void duelistCoverTrap(
            @PathVariable String uuid,
            @PathVariable Side side,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDto cardReqDto,
            Principal principal) {
        if (skip) {
            findDuelFieldByUuid(uuid).duelistSkipCoverTrap(side);
        } else {
            findDuelFieldByUuid(uuid).duelistCoverTrap(side, cardReqDto.getUuid(), cardReqDto.getState(), principal.getName());
        }
        findDuelFieldByUuid(uuid).skipFirstRoundBattle();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:startBattle")
    public void duelistStartBattle(
            @PathVariable String uuid,
            @PathVariable Side side,
            @RequestParam(required = false, defaultValue = "false") Boolean skip,
            @RequestBody CardReqDto cardReqDto,
            Principal principal) {
        if (skip) {
            findDuelFieldByUuid(uuid).duelistSkipStartBattle(side);
        } else {
            findDuelFieldByUuid(uuid).duelistStartBattle(side, cardReqDto.getUuid(), principal.getName());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelField/{uuid}")
    public DuelFieldDto queryDuelField(
            @PathVariable String uuid,
            Principal principal) {

        return findDuelFieldByUuid(uuid).toDto(
                findDuelFieldByUuid(uuid).checkIsDuelist(Side.LEFT, principal.getName()),
                findDuelFieldByUuid(uuid).checkIsDuelist(Side.RIGHT, principal.getName())
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelField/{uuid}:sse")
    public SseEmitter listenDuelField(@PathVariable String uuid) throws IOException {
        final SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.send(
                SseEmitter
                        .event()
                        .id(LocalDateTime.now().toString())
                        .name("Connected, you're listening " + uuid + " duel field.")
                        .reconnectTime(1000)
        );

        return networkManager.register(uuid, sseEmitter);
    }

    private DuelField findDuelFieldByUuid(String uuid) {
        return Optional
                .of(gameManager.findDuelFieldByUuid(uuid))
                .orElseThrow(() -> new DuelFieldNotFound("DuelField with " + uuid + " not found"));
    }
}
