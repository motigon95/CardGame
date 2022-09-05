package com.example.cardgame3.repository;

import com.example.cardgame3.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Integer> {

    @Query("select p from Player p")
    List<Player> getAllPlayers();
    @Query("select p from Player p where p.playerName = :name")
    Player getPlayerByName(String name);
}
