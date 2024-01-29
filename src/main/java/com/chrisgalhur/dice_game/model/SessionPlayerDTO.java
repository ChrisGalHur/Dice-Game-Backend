package com.chrisgalhur.dice_game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Manage a player session out of the database.
 *
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionPlayerDTO {

    @Id
    private String id;

    private String name;

    private String password;
}
