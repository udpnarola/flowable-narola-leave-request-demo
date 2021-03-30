package com.narola.flowablenarolaleaverequestdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(proxyBeanMethods = false)
public class FlowableNarolaLeaveRequestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableNarolaLeaveRequestDemoApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
