package ru.loogika.javabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Куда сервер будет посылать сообщения (фронту и боту)
        config.enableSimpleBroker(
                "/topic/frontend",
                "/topic/bot"
        );

        // Префиксы, по которым приходят команды от клиентов
        config.setApplicationDestinationPrefixes(
                "/app/frontend",
                "/app/bot"
        );
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint для React фронта
        registry.addEndpoint("/ws/frontend")
                .setAllowedOriginPatterns("*")
                .withSockJS();  // фронту удобно через SockJS

        // Endpoint для Python-бота (можно без SockJS, чистый WebSocket)
        registry.addEndpoint("/ws/bot")
                .setAllowedOriginPatterns("*");
    }
}
