package com.example.springboot2webclientsyncexample;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    @Bean
    WebClient webClient() {
        return WebClient
                .builder()
                .clientConnector(jettyClientHttpConnector())
                .build();
    }

    /*
     * To fix "java.lang.NullPointerException: Missing SslContextFactory"
     *
     * https://github.com/spring-projects/spring-boot/issues/16810#issue-442290555
     */
    @Bean
    JettyClientHttpConnector jettyClientHttpConnector()  {
        SslContextFactory ssl = new SslContextFactory();
        HttpClient httpClient = new  HttpClient(ssl);
        return new JettyClientHttpConnector(httpClient);
    }
}
