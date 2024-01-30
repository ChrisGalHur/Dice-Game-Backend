package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.DataPlayerEntity;
import com.chrisgalhur.dice_game.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * Manages player data in the communication with the database.
 * This class represents the main player entity, excluding sensitive information like password and roles,
 * which are managed by the authentication and registration process handled by a separate class.
 *
 * @see com.chrisgalhur.dice_game.model.SessionPlayerDTO DTO of this class.
 * @see com.chrisgalhur.dice_game.model.SessionPlayer Login and registration process.
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Player")
public class Player {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "registration")
    private Date registration;

    @Field(name = "role")
    private List<Role> role;

    @Field(name = "history")
    private List<DataPlayerEntity> dataPlayer;
}
