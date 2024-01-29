package com.chrisgalhur.dice_game.repository;

import com.chrisgalhur.dice_game.model.SessionPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionPlayerRepository extends MongoRepository<SessionPlayer, String> {

    Optional<SessionPlayer> findByName(String username);

    boolean existsByName(String name);

    SessionPlayer save(SessionPlayer player);
}
