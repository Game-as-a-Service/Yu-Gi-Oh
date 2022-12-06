package tw.gaas.yugioh.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.purefunc.emoji.Emoji2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.gaas.yugioh.data.dto.CardReqDto;
import tw.gaas.yugioh.data.dto.DuelFieldDto;
import tw.gaas.yugioh.data.entity.DuelField;
import tw.gaas.yugioh.data.entity.Duelist;
import tw.gaas.yugioh.data.entity.Zone;
import tw.gaas.yugioh.data.enu.Phase;
import tw.gaas.yugioh.data.enu.Side;
import tw.gaas.yugioh.manager.GameManager;
import tw.gaas.yugioh.manager.NetworkManager;
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
    private final NetworkManager networkManager;

    public DuelFieldController(GameManager gameManager, NetworkManager networkManager) {
        this.gameManager = gameManager;
        this.networkManager = networkManager;
    }

    @GetMapping("/status")
    public String getStatus() {
        return String.format("Hello Duelist! %s%s%s", Emoji2.MILITARY_MEDAL, Emoji2.MILITARY_MEDAL, Emoji2.MILITARY_MEDAL);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField:join")
    public Map<String, String> joinFuelField(Principal principal) {
        final String pairDuelFieldUuid = gameManager.requestPair();
        final String duelFieldUuid = UUID.randomUUID().toString();

        final Duelist duelist = new Duelist(principal.getName());
        final Zone zone = new Zone(duelist);

        if (isLeftDuelist(pairDuelFieldUuid)) {
            final DuelField duelField = new DuelField(duelFieldUuid);
            duelField.setLeft(zone);
            duelField.waitDuelist();

            gameManager.putDuelFieldWithUuid(duelFieldUuid, duelField);
            gameManager.submitPairRequest(duelFieldUuid);

            return Map.of(
                    "sse", "curl -H \"Accept:text/event-stream\" http://localhost:8080/java/api/v1.0/duelField/" + duelFieldUuid + ":sse",
                    "duelFieldUuid", duelFieldUuid
            );
        } else {
            final DuelField duelField = gameManager.findDuelFieldByUuid(pairDuelFieldUuid);
            duelField.setRight(zone);
            duelField.start();

            return Map.of(
                    "sse", "curl -H \"Accept:text/event-stream\" http://localhost:8080/java/api/v1.0/duelField/" + pairDuelFieldUuid + ":sse",
                    "duelFieldUuid", pairDuelFieldUuid
            );
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:drawCard")
    public void duelistDrawCard(@PathVariable String uuid, @PathVariable Side side, Principal principal) {
        final DuelField duelField = Optional
                .of(gameManager.findDuelFieldByUuid(uuid))
                .orElseThrow(() -> new DuelFieldNotFound("DuelField with " + uuid + " not found"));
        final Phase phase = side == Side.LEFT ? Phase.LEFT_DRAW : Phase.RIGHT_DRAW;
        duelField.validPhase(phase);
        duelField.validDuelist(side, principal.getName());

        duelField.duelistDraw(side);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:summonMonster")
    public void duelistSummonMonster(@PathVariable String uuid, @PathVariable Side side, @RequestParam(required = false, defaultValue = "false") Boolean skip, @RequestBody CardReqDto cardReqDto, Principal principal) {
        final DuelField duelField = Optional
                .of(gameManager.findDuelFieldByUuid(uuid))
                .orElseThrow(() -> new DuelFieldNotFound("DuelField with " + uuid + " not found"));
        final Phase phase = side == Side.LEFT ? Phase.LEFT_MONSTER : Phase.RIGHT_MONSTER;
        duelField.validPhase(phase);
        duelField.validDuelist(side, principal.getName());

        if (skip) {
            duelField.duelistSkipSummonMonster(side);
        } else {
            duelField.validDuelistSummonMonster(side, cardReqDto.getUuid());
            duelField.duelistSummonMonster(side, cardReqDto.getUuid(), cardReqDto.getState());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:applySpell")
    public void duelistApplySpell(@PathVariable String uuid, @PathVariable Side side, @RequestParam(required = false, defaultValue = "false") Boolean skip, @RequestBody CardReqDto cardReqDto, Principal principal) {
        final DuelField duelField = Optional
                .of(gameManager.findDuelFieldByUuid(uuid))
                .orElseThrow(() -> new DuelFieldNotFound("DuelField with " + uuid + " not found"));
        final Phase phase = side == Side.LEFT ? Phase.LEFT_SPELL : Phase.RIGHT_SPELL;
        duelField.validPhase(phase);
        duelField.validDuelist(side, principal.getName());

        if (skip) {
            duelField.duelistSkipApplySpell(side);
        } else {
            duelField.validDuelistApplySpell(side, cardReqDto.getUuid());
            duelField.duelistApplySpell(side, cardReqDto.getUuid());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:coverTrap")
    public void duelistCoverTrap(@PathVariable String uuid, @PathVariable Side side, @RequestParam(required = false, defaultValue = "false") Boolean skip, @RequestBody CardReqDto cardReqDto, Principal principal) {
        final DuelField duelField = Optional
                .of(gameManager.findDuelFieldByUuid(uuid))
                .orElseThrow(() -> new DuelFieldNotFound("DuelField with " + uuid + " not found"));
        final Phase phase = side == Side.LEFT ? Phase.LEFT_TRAP : Phase.RIGHT_TRAP;
        duelField.validPhase(phase);
        duelField.validDuelist(side, principal.getName());

        if (skip) {
            duelField.duelistSkipCoverTrap(side);
        } else {
            duelField.validDuelistCoverTrap(side, cardReqDto.getUuid());
            duelField.duelistCoverTrap(side, cardReqDto.getUuid(), cardReqDto.getState());
        }

        duelField.skipFirstRoundBattle();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/duelField/{uuid}/{side}:startBattle")
    public void duelistStartBattle(@PathVariable String uuid, @PathVariable Side side, @RequestParam(required = false, defaultValue = "false") Boolean skip, @RequestBody CardReqDto cardReqDto, Principal principal) {
        final DuelField duelField = Optional
                .of(gameManager.findDuelFieldByUuid(uuid))
                .orElseThrow(() -> new DuelFieldNotFound("DuelField with " + uuid + " not found"));
        final Phase phase = side == Side.LEFT ? Phase.LEFT_BATTLE : Phase.RIGHT_BATTLE;
        duelField.validPhase(phase);
        duelField.validDuelist(side, principal.getName());

        if (skip) {
            duelField.duelistSkipStartBattle(side);
        } else {
            duelField.validDuelistStartBattle(side, cardReqDto.getUuid());
            duelField.duelistStartBattle(side, cardReqDto.getUuid());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelField/{uuid}")
    public DuelFieldDto queryDuelField(@PathVariable String uuid, Principal principal) {
        final DuelField duelField = gameManager.findDuelFieldByUuid(uuid);
        final Boolean isLeftDuelist = duelField.checkIsDuelist(Side.LEFT, principal.getName());
        final Boolean isRightDuelist = duelField.checkIsDuelist(Side.RIGHT, principal.getName());

        return duelField.toDto(isLeftDuelist, isRightDuelist);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/duelField/{uuid}/:sse")
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

    private boolean isLeftDuelist(String pairDuelFieldUuid) {
        return pairDuelFieldUuid == null;
    }
}
