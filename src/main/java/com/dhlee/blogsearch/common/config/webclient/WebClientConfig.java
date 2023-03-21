package com.dhlee.blogsearch.common.config.webclient;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
	private static final int DEFAULT_TIMEOUT = 5000;

	@Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
										  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, DEFAULT_TIMEOUT)
										  .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS))
																	 .addHandlerLast(new WriteTimeoutHandler(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)));
		return WebClient.builder()
						.clientConnector(new ReactorClientHttpConnector(httpClient))
						.build();
	}
}
