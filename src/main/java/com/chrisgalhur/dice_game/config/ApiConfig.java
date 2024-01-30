package com.chrisgalhur.dice_game.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for API-related beans and components.
 * This class is responsible for configuring and providing necessary beans for the API module.
 *
 * <p>
 * The main responsibilities include:
 * - Defining a bean for {@link org.modelmapper.ModelMapper}.
 * </p>
 *
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Configuration
public class ApiConfig {

    /**
     * Creates and configures a {@link org.modelmapper.ModelMapper} bean.
     *
     * @return Configured ModelMapper bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}