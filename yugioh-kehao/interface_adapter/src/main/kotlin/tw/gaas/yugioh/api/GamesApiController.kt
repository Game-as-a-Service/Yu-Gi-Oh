package tw.gaas.yugioh.api

import tw.gaas.yugioh.model.resp.FindAvailableGameResp
import tw.gaas.yugioh.model.resp.GetSpecifiedGameResp
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Slf4j
@RestController
class GamesApiController @Autowired constructor() : GamesApi {
    override fun findAvailableGame(
        @Parameter(
            `in` = ParameterIn.DEFAULT,
            description = "要求可用的對局"
        ) @RequestBody body: @Valid Any?
    ): ResponseEntity<FindAvailableGameResp?>? {
        val resp = FindAvailableGameResp()
        return ResponseEntity(resp, HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getGameGameId(
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "對局 ID",
            required = true
        ) @PathVariable("gameId") gameId: String?
    ): ResponseEntity<GetSpecifiedGameResp?>? {
        val resp = GetSpecifiedGameResp()
        return ResponseEntity(resp, HttpStatus.NOT_IMPLEMENTED)
    }
}
