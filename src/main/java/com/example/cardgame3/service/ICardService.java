package com.example.cardgame3.service;

import com.example.cardgame3.dto.CardDTO;
import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;
import com.example.cardgame3.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICardService {

    ResponseEntity<?> saveCard(CardDTO cardDTO);

    ResponseEntity<?> saveMultipleCards(List<Card> cards);

//    ResponseEntity<Page<Card>> getCards(CardPage cardPage);

    ResponseEntity<List<Card>> getAllCards();

    ResponseEntity<List<Card>> assignRandomDeck(PlayerDTO playerDTO);
}
