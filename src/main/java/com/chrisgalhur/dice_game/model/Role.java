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
 * @author ChrisGalHur
 */

@Data
@AllArgsConstructor
public class Role {

    //region ATTRIBUTES
    private String name;
    //endregion ATTRIBUTES
}
