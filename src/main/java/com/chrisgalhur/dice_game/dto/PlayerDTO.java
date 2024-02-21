package com.chrisgalhur.dice_game.dto;

import com.chrisgalhur.dice_game.entity.DataPlayer;
import com.chrisgalhur.dice_game.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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
    private UUID id;

    private String name;

    private LocalDateTime registration;

    private List<Role> role;

    private List<DataPlayer> dataPlayer;
    //endregion ATTRIBUTES
}
