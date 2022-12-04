package tw.gaas.yugioh.api

import tw.gaas.yugioh.model.resp.FindAvailableGameResp
import tw.gaas.yugioh.model.resp.GetSpecifiedGameResp
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Validated
interface GamesApi {
    @Operation(
        summary = "尋找可用的對局",
        description = "尋找可用的對局 - 若有等待成立的對局，則加入既有等待玩家加入的對局 - 若無等待成立的對局，則創立新對局等待其他玩家加入",
        tags = ["game"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "回應可用的對局",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = FindAvailableGameResp::class)
                )
            )
        )]
    )
    @RequestMapping(
        value = ["/games/find"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun findAvailableGame(
        @Parameter(
            `in` = ParameterIn.DEFAULT,
            description = "要求可用的對局"
        ) @RequestBody body: @Valid Any?
    ): ResponseEntity<FindAvailableGameResp?>?

    @Operation(
        summary = "取得指定的對局",
        description = "取得指定的對局，在專案實作初期應是用於定時 Polling 確認對局是否成立，可開始進行遊戲",
        tags = ["game"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "回應指定的對局",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = GetSpecifiedGameResp::class)
                )
            )
        )]
    )
    @RequestMapping(value = ["/games/{gameId}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getGameGameId(
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "對局 ID",
            required = true
        ) @PathVariable("gameId") gameId: String?
    ): ResponseEntity<GetSpecifiedGameResp?>?
}
