package com.chrisgalhur.dice_game.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for API-related beans and components.
 * This class is responsible for configuring and providing necessary beans for the API module.
 *
 * <ul>
 *      <li>The main responsibilities include:
 *          <ul>
 *              <li>Defining a bean for ModelMapper.</li>
 *          </ul>
 *      </li>
 * </ul>
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Configuration
public class ApiConfig {

    //region MODEL MAPPER
    /**
     * Creates and configures a {@link org.modelmapper.ModelMapper} bean.
     *
     * @return Configured ModelMapper bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    //endregion MODEL MAPPER
}
