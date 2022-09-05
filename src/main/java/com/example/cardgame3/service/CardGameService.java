package com.example.cardgame3.service;

import com.example.cardgame3.dto.CardDTO;
import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;

import com.example.cardgame3.model.Player;
import com.example.cardgame3.repository.ICardRepository;
import com.example.cardgame3.repository.IPlayerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardGameService implements ICardService, IPlayerService{

    @Autowired
    ICardRepository cardRepository;

    @Autowired
    IPlayerRepository playerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> saveCard(CardDTO cardDTO) {
        Card cardToSave = modelMapper.map(cardDTO, new TypeToken<Card>(){}.getType());
        cardRepository.save(cardToSave);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> saveMultipleCards(List<Card> cards) {
        cardRepository.saveAll(cards);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> savePlayer(PlayerDTO playerDTO) {
        Player playerToSave = modelMapper.map(playerDTO, new TypeToken<Player>(){}.getType());
        playerRepository.save(playerToSave);
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

        Player filteredPlayer = playerRepository.getPlayerByName(playerDTO.getPlayerName());
        if(filteredPlayer == null) {
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

    private Card assignCard(){
        //IF list esta vacia hacer algo
        List<Card> allCards = cardRepository.getAllCardsSaved();
        Random rnd = new Random();

        //ejemplo de porcentaje
        Integer rarity = rnd.nextInt(100);
        Card cardToAssign;
        if (rarity <= 70){
            List<Card> commonCards = allCards.stream().filter(card -> card.getRarity().equals("common")).collect(Collectors.toList());
            cardToAssign = commonCards.get(rnd.nextInt(commonCards.size()));
        } else if (rarity <=90) {
            List<Card> rareCards = allCards.stream().filter(card -> card.getRarity().equals("rare")).collect(Collectors.toList());
            cardToAssign = rareCards.get(rnd.nextInt(rareCards.size()));
        } else {
            List<Card> uniqueCards = allCards.stream().filter(card -> card.getRarity().equals("unique")).collect(Collectors.toList());
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
