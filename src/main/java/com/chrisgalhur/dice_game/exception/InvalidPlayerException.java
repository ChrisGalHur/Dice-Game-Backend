package com.chrisgalhur.dice_game.exception;

import lombok.Getter;

/**
 * Exception class to manage invalid player.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Getter
public class InvalidPlayerException extends RuntimeException{

        //region ATTRIBUTES
        /**
         * Error message to return on endpoint whit invalid player.
         */
        private final String errorMessage;
        //endregion ATTRIBUTES

        //region CONSTRUCTOR
        /**
        * Constructor of the class.
        *
        * @param errorMessage Error message.
        */
        public InvalidPlayerException(String errorMessage) {
            this.errorMessage = errorMessage;
        }
        //endregion CONSTRUCTOR
}
