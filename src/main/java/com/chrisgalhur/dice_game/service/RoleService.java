package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.Role;

import java.util.Optional;

/**
 * Interface to manage the role service.
 *
 * @version 1.0
 * @author ChrisGalHur
 */

public interface RoleService {

    //region FIND BY NAME
    /**
     * Method to find a role by name.
     *
     * @param user Name of the role.
     * @return The role.
     */
    Optional<Role> findByName(String user);
    //endregion FIND BY NAME
}
