package tw.wsa.yugioh.web.ws;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;
import tw.wsa.yugioh.config.Constant;
import tw.wsa.yugioh.config.CoreConfig;
import tw.wsa.yugioh.config.SpringContext;
import tw.wsa.yugioh.service.MsgService;

@Slf4j
public class WsServerVerticle extends AbstractVerticle {

    @Override
    public void start() {
        final MsgService msgService = SpringContext.getBean("msgService", MsgService.class);
        final CoreConfig coreConfig = SpringContext.getBean("coreConfig", CoreConfig.class);

        vertx.createHttpServer()
                .connectionHandler(httpConnection -> log.info(String.format("client connecting %s", httpConnection.remoteAddress())))
                .webSocketHandler(serverWebSocket -> {
                    final RecordParser recordParser = RecordParser.newDelimited(Constant.CR, serverWebSocket);
                    recordParser.handler(buffer -> msgService.handleMsg(serverWebSocket, buffer.toString()));
                    recordParser.endHandler(empty -> msgService.cleanSession(serverWebSocket, "Socket End"));
                })
                .listen(coreConfig.getPort(), ws -> {
                    if (ws.succeeded()) {
                        log.info("ws server started on port {}", coreConfig.getPort());
                    } else {
                        log.error(ws.cause().toString(), ws.cause());
                    }
                });
    }
}
