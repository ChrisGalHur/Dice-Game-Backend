package com.chrisgalhur.dice_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Manage a player session out of the database.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionPlayerDTO {

    //region ATTRIBUTES
    @Id
    private String id;

    private String name;

    private String password;
    //endregion ATTRIBUTES
}
