package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Manage the role repository on the database.
 *
 *
 * @see com.chrisgalhur.dice_game.model.Role
 * @version 1.0
 * @since 2024-01-30
 * @author ChrisGalHur
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {


    Optional<Role> findByName(String user);
}
