package com.test.Aptitude.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.Aptitude.DTOs.Input.ClientOrder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelJacksonConfig {

    @Bean
    public JacksonDataFormat clientOrderJacksonDataFormat() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JacksonDataFormat format = new JacksonDataFormat(ClientOrder.class);
        format.setObjectMapper(mapper);
        return format;
    }
}