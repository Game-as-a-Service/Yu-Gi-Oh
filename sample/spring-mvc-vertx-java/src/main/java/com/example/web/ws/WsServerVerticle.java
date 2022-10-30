package com.example.web.ws;

import com.example.config.Constant;
import com.example.config.CoreConfig;
import com.example.config.SpringContext;
import com.example.service.MessageService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WsServerVerticle extends AbstractVerticle {

    @Override
    public void start() {
        final MessageService messageService = SpringContext.getBean("messageService", MessageService.class);
        final CoreConfig coreConfig = SpringContext.getBean("coreConfig", CoreConfig.class);

        vertx
                .createHttpServer()
                .connectionHandler(httpConnection -> log.info(String.format("client connecting %s", httpConnection.remoteAddress())))
                .webSocketHandler(serverWebSocket -> {
                    final RecordParser recordParser = RecordParser.newDelimited(Constant.CR, serverWebSocket);
                    recordParser.handler(buffer -> messageService.handleMsg(serverWebSocket, buffer.toString()));
                    recordParser.endHandler(empty -> messageService.cleanSession(serverWebSocket, "Socket End"));
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
