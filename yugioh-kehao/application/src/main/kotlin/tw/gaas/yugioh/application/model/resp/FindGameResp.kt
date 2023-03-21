package tw.gaas.yugioh.application.model.resp

import lombok.Data
import tw.gaas.yugioh.domain.entity.Game
import tw.gaas.yugioh.domain.event.DomainEvent

@Data
class FindGameResp(
    game: Game? = null,
    error: Exception? = null,
) : BasicResp(error)
