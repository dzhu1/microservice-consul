package com.microservice.framework.consul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orbitz.consul.Consul;

@Configuration
public class ConsulConfig {

    @Bean
    public Consul consul(){
        return Consul.builder().build();
    }
}
