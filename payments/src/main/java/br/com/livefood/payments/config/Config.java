package br.com.livefood.payments.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rafae
 */
@Configuration
public class Config {

    //Resolução para injeção de dependências
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
