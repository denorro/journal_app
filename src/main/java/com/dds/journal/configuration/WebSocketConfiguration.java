package com.dds.journal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		//STOMP will use this url to connect to the socket
		registry.addEndpoint("/ws").withSockJS();		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		//send messages to this url so it can be broadcasted
		registry.enableSimpleBroker("/chat", "/topic/", "/queue/");
		
		//app root
		registry.setApplicationDestinationPrefixes("/app");
	}

}
