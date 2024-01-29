package com.chrisgalhur.dice_game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Manage a player session in the database.
 *
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Player_session")
public class SessionPlayer {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "password")
    private String password;

    @Field(name = "roles")
    private List<Role> roles;
}
