package com.chrisgalhur.dice_game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Manage a game by listing the dice and the result.
 *
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
 @Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPlayerEntity {

    @Id
    @Field(name = "id")
    private String id;
    @Field(name = "Dice 1")
    private int numDice1;
    @Field(name = "Dice 2")
    private int numDice2;
    @Field(name = "result")
    private String result;

    /**
     * Constructor of DataPlayerEntity to register a new game.
     *
     * @param numDice1 Result of the first dice.
     * @param numDice2 Result of the second dice.
     * @param result Result of the game.
     */
    public DataPlayerEntity(int numDice1, int numDice2, String result) {
        this.numDice1 = numDice1;
        this.numDice2 = numDice2;
        this.result = result;
    }
}