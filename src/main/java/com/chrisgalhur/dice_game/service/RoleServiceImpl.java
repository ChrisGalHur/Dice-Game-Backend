package com.chrisgalhur.dice_game.service;

import com.chrisgalhur.dice_game.model.Role;
import com.chrisgalhur.dice_game.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class to manage the role service.
 * Implements RoleService interface.
 *
 * @see RoleService
 *
 * @version 1.0
 * @author ChrisGalHur
 */

@Service
public class RoleServiceImpl implements RoleService {

    //region DEPENDENCY INJECTION
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    //endregion DEPENDENCY INJECTION

    //region FIND BY NAME
    @Override
    public Optional<Role> findByName(String user) {
        return roleRepository.findByName(user);
    }
    //endregion FIND BY NAME
}
