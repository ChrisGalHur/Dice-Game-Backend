package com.chrisgalhur.dice_game.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

    //region OPENAPI CONFIGURATION
    /**
     * Configures the OpenAPI documentation for the API.
     * Not implemented yet.
     *
     * @return OpenAPI object containing the API documentation.
     */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Dice Game API")
                        .version("1.0")
                        .description("RESTful API for the Dice Game application with JWT authentication."));
    }
    //endregion OPENAPI CONFIGURATION
}
