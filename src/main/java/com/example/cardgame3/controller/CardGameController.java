package com.example.cardgame3.controller;

import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;
import com.example.cardgame3.model.Player;
import com.example.cardgame3.service.CardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardGameController {

    @Autowired
    CardGameService cardGameService;


    @PutMapping("/card")
    public ResponseEntity<?> saveCard(@RequestBody Card card){
        return cardGameService.saveCard(card);
    }


    @PutMapping("/cards")
    public ResponseEntity<?> saveMultipleCards(@RequestBody List<Card> cards){
        return cardGameService.saveMultipleCards(cards);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards(){
        return cardGameService.getAllCards();
    }

    @GetMapping("/deck")
    public ResponseEntity<List<Card>> getAssignedDeck(@RequestBody PlayerDTO playerDTO){
        return cardGameService.assignRandomDeck(playerDTO);
    }

    @GetMapping("/playcard")
    public ResponseEntity<Card> playCard(@RequestBody Player player, @RequestParam Integer cardNumber){
        return cardGameService.playCard(player,cardNumber);
    }
//    public ResponseEntity<Page<Card>> getItems(CardPage cardPage){
//        return cardGameService.getCards(cardPage);
//    }

    @PutMapping("/player")
    public ResponseEntity<?> loadPlayer(@RequestBody Player player){
        return cardGameService.savePlayer(player);
    }



    @GetMapping("/players")
    public ResponseEntity<?> getAllPlayers(){
        return cardGameService.getAllPlayers();
    }


}
