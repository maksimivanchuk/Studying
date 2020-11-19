package com.grom.music.common;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.grom.music")
@EnableJpaRepositories("com.grom.music")
@EntityScan("com.grom.music")
public class GlobalConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
