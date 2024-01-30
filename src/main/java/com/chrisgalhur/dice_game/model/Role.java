package com.chrisgalhur.dice_game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Specified a role of player can be in the game.
 *
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Role")
public class Role {

    @Id
    private String id;

    private String name;
}
