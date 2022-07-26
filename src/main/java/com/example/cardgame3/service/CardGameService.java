package com.example.cardgame3.service;

import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;

import com.example.cardgame3.model.Player;
import com.example.cardgame3.repository.ICardRepository;
import com.example.cardgame3.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CardGameService implements ICardService, IPlayerService{

    @Autowired
    ICardRepository cardRepository;

    @Autowired
    IPlayerRepository playerRepository;

    @Override
    public ResponseEntity<?> saveCard(Card card) {
        cardRepository.save(card);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> saveMultipleCards(List<Card> cards) {
        cardRepository.saveAll(cards);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> savePlayer(Player player) {
        playerRepository.save(player);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> allPlayers = playerRepository.getAllPlayers();
        return new ResponseEntity<>(allPlayers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Card> playCard(Player player, Integer cardNumber) {
        List<Player> allPlayers = playerRepository.getAllPlayers();
        String playerName = player.getPlayerName();
        List<Player> filteredList = allPlayers.stream().filter(player1 -> player1.getPlayerName().equals(playerName)).collect(Collectors.toList());
        if(filteredList.size()==1){
            player = filteredList.get(0);
        } else {
            return new ResponseEntity<>(cardRepository.getAllCardsSaved().get(cardNumber), HttpStatus.NOT_FOUND);
        }
        Card cardToPlay = player.getCardDeck().get(cardNumber);
        return new ResponseEntity<>(cardToPlay, HttpStatus.OK);
    }


    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> allCards = cardRepository.getAllCardsSaved();
        return new ResponseEntity<>(allCards, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Card>> assignRandomDeck(PlayerDTO playerDTO) {
        // Aca igualo el DTO a un player existente por su nombre
        List<Player> allPlayers = playerRepository.getAllPlayers();
        String playerName = playerDTO.getPlayerName();
        Player filteredPlayer = allPlayers.stream().filter(player1 -> player1.getPlayerName().equals(playerName)).findFirst().get();
        if(filteredPlayer==null){
            return new ResponseEntity<>(cardRepository.getAllCardsSaved(), HttpStatus.NOT_FOUND);
        }

        for (int i=0; i<3; i++){
            Card card = assignCard();
            if (!filteredPlayer.getCardDeck().contains(card)){
                filteredPlayer.getCardDeck().add(card);
            }else i--;
        }
        playerRepository.save(filteredPlayer);
        return new ResponseEntity<>(filteredPlayer.getCardDeck(), HttpStatus.OK);
    }

    public Card assignCard(){
        //IF list esta vacia hacer algo
        List<Card> availableCards = cardRepository.getAvailableCards();
        Random rnd = new Random();

        //ejemplo de porcentaje
        Integer rarity = rnd.nextInt(100);
        Card cardToAssign;
        if (rarity <= 70){
            List<Card> commonCards = availableCards.stream().filter(card -> card.getRarity().equals("common")).collect(Collectors.toList());
            cardToAssign = commonCards.get(rnd.nextInt(commonCards.size()));
        } else if (rarity <=90) {
            List<Card> rareCards = availableCards.stream().filter(card -> card.getRarity().equals("rare")).collect(Collectors.toList());
            cardToAssign = rareCards.get(rnd.nextInt(rareCards.size()));
        } else {
            List<Card> uniqueCards = availableCards.stream().filter(card -> card.getRarity().equals("unique")).collect(Collectors.toList());
            cardToAssign = uniqueCards.get(rnd.nextInt(uniqueCards.size()));
        }
        return cardToAssign;
    }

    @Override
    public ResponseEntity<?> combatPlayers(Player player1, Player player2) {
        Card player1ChosenCard = player1.playCard(0);
        Integer player1ChosenCardAttack = player1ChosenCard.getAttackPoints();
        Integer player1ChosenCardDefense = player1ChosenCard.getDefensePoints();
        Card player2ChosenCard = player2.playCard(0);
        Integer player2ChosenCardAttack = player2ChosenCard.getAttackPoints();
        Integer player2ChosenCardDefense = player2ChosenCard.getDefensePoints();



        return null;
    }

}
