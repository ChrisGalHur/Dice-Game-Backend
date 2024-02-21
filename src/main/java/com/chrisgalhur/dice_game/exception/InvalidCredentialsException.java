package com.chrisgalhur.dice_game.exception;

import lombok.Getter;

/**
 * Exception class to manage invalid credentials.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Getter
public class InvalidCredentialsException extends RuntimeException {

    //region ATTRIBUTES
    /**
     * Error message to return on endpoint whit invalid credentials.
     */
    private final String errorMessage;
    //endregion ATTRIBUTES

    //region CONSTRUCTOR
    /**
     * Constructor of the class.
     *
     * @param errorMessage Error message.
     */
    public InvalidCredentialsException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    //endregion CONSTRUCTOR
}
