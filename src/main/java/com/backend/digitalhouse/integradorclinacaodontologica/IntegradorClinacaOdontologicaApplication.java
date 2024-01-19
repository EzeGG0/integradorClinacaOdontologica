package com.backend.digitalhouse.integradorclinacaodontologica;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntegradorClinacaOdontologicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegradorClinacaOdontologicaApplication.class, args);
    }

    @Bean
    public ModelMapper configuration() {
        return new ModelMapper();
    }

}
