package com.chrisgalhur.dice_game.entity;

import com.chrisgalhur.dice_game.dto.SessionPlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Manages player data in the communication with the database.
 * This class represents the main player entity, excluding sensitive information like password and roles,
 * which are managed by the authentication and registration process handled by a separate class.
 *
 * @see SessionPlayerDTO DTO of this class.
 * @see com.chrisgalhur.dice_game.entity.SessionPlayer Login and registration process.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Player")
public class Player {

    //region ATTRIBUTES
    @Id
    private UUID id;

    @Field(name = "name")
    private String name;

    @Field(name = "registration")
    private LocalDateTime registration;

    @Field(name = "roles")
    private List<Role> roles;

    @Field(name = "history")
    private List<DataPlayer> dataPlayer;
    //endregion ATTRIBUTES
}
