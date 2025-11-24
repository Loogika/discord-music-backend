package ru.loogika.javabackend.websocket.frontend;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class FrontendWsController {

    // Клиент SEND → "/app/frontend/echo"
    @MessageMapping("/echo")
    @SendTo("/topic/frontend/echo")
    public String echoFromFrontend(String payload) {
        // просто возвращаем строку обратно с пометкой
        return "frontend: " + payload;
    }
}
