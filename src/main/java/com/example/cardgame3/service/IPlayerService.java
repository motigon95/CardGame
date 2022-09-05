package com.example.cardgame3.service;

import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;
import com.example.cardgame3.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPlayerService {

    ResponseEntity<?> savePlayer(PlayerDTO playerDTO);

    ResponseEntity<List<Player>> getAllPlayers();

    ResponseEntity<Card> playCard(Player player, Integer cardNumber);

    ResponseEntity<?> combatPlayers(Player player1, Player player2);

}
