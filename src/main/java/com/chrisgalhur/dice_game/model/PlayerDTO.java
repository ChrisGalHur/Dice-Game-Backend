package com.chrisgalhur.dice_game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import java.util.List;

/**
 * Manage the data of player and her history out of database.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    //region ATTRIBUTES
    @Id
    private String id;

    private String name;

    private Date registration;

    private List<Role> role;

    private List<DataPlayerEntity> dataPlayer;
    //endregion ATTRIBUTES
}
