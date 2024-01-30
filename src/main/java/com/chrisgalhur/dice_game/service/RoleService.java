package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.Role;

import java.util.Optional;

/**
 * Interface to manage the role service.
 *
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
public interface RoleService {

    Optional<Role> findByName(String user);
}
