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
 * Class to manage the data of player.
 *
 * @version 1.0
 * @since 2024-01-29
 * @author ChrisGalHur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Player")
public class PlayerDTO {

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
