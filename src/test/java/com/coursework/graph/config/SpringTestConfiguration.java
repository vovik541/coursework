package com.coursework.graph.config;

import com.coursework.graph.service.serializer.JsonSerializerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfiguration {

    @Bean
    public JsonSerializerService createJsonSerializerService(){
        return new JsonSerializerService();
    }
}
