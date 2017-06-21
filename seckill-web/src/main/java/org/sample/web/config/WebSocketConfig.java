package org.sample.web.config;

import org.sample.web.component.WebSocketInterceptor;
import org.sample.web.component.WebSocketMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public WebSocketMessageHandler myWebSocket() {
        return new WebSocketMessageHandler();
    }

    @Bean
    public WebSocketInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocket(), "/websocket").addInterceptors(webSocketInterceptor());
        registry.addHandler(myWebSocket(), "/sockjs").addInterceptors(webSocketInterceptor()).withSockJS();
    }
}