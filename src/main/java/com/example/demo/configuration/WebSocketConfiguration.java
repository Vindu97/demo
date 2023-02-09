package com.example.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSocket
@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final com.example.demo.controller.VideoSocketHandler VideoSocketHandler; 
    private final ConfigReader configurations;

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer(){
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(1024000);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(VideoSocketHandler, configurations.getVideoWsEndpoint()).setAllowedOrigins("*");
        
    }
    
}
