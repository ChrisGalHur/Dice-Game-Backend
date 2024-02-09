package com.chrisgalhur.dice_game.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

/**
 * Manages the player session in the database.
 * Only to store the name, password and roles are stored.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Session_Player")
public class SessionPlayer {

    //region ATTRIBUTES
    @Id
    private UUID id;

    @Field(name = "name")
    private String name;

    @Field(name = "password")
    private String password;

    @Field(name = "roles")
    private List<Role> roles;
    //endregion ATTRIBUTES
}
