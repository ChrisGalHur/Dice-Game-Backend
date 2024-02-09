package com.chrisgalhur.dice_game.dto;

import com.chrisgalhur.dice_game.entity.DataPlayer;
import com.chrisgalhur.dice_game.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private UUID id;

    private String name;

    private Date registration;

    private List<Role> role;

    private List<DataPlayer> dataPlayer;
    //endregion ATTRIBUTES
}
