package com.example.cardgame3.controller;

import com.example.cardgame3.dto.CardDTO;
import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;
import com.example.cardgame3.service.CardGameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class CardGameControllerTest {

    @Mock
    private CardGameService cardGameService;

    @InjectMocks
    private CardGameController cardGameController;

    private static List<Card> cardList = new ArrayList<>();

    @BeforeAll
    public static void setUp(){
        //Arrange
        Card card = Card.builder()
                .cardName("Lote")
                .attackPoints(1)
                .defensePoints(1)
                .rarity("rare")
                .build();

        Card card2 = Card.builder()
                .cardName("Lote")
                .attackPoints(1)
                .defensePoints(1)
                .rarity("rare")
                .build();

        cardList.add(card);
        cardList.add(card2);
    }

    @Test
    public void shouldSaveCardSuccessfully() {
        //arrange
        CardDTO card = CardDTO.builder()
                .cardName("Lote")
                .attackPoints(1)
                .defensePoints(1)
                .rarity("rare")
                .build();
        when(cardGameService.saveCard(card)).thenReturn(ResponseEntity.ok().build());
        //act
        ResponseEntity<?> actualResponse = cardGameController.saveCard(card);
        //assert
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void shouldSaveMultipleCardsSuccessfully() {
        //arrange
        when(cardGameService.saveMultipleCards(cardList)).thenReturn(ResponseEntity.ok().build());

        //act
        ResponseEntity<?> actualResponse = cardGameController.saveMultipleCards(cardList);
        //assert
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }

    @Test
    public void shouldGetAllCardsSuccessfully() {
        //arrange
        when(cardGameService.getAllCards()).thenReturn(ResponseEntity.ok().build());
        //act
        ResponseEntity<?> actualResponse = cardGameController.getAllCards();
        //assert
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getAllCardsShouldReturnCardListSuccessfully(){
        //arrange
        when(cardGameService.getAllCards()).thenReturn(new ResponseEntity<>(cardList, HttpStatus.OK));
        //act
        ResponseEntity<List<Card>> actualResponse = cardGameController.getAllCards();
        List<Card> actualList = actualResponse.getBody();
        //assert
        assertEquals(cardList.size(), actualList.size());
        verify(cardGameService, atMostOnce()).getAllCards();
    }

    @Test
    public void shouldAssignDeckSuccessfully() {
        //arrange
        PlayerDTO playerDTO = new PlayerDTO("Moti");

        when(cardGameService.assignRandomDeck(playerDTO)).thenReturn(ResponseEntity.ok().build());

        //act
        ResponseEntity<?> actualResponse = cardGameController.getAssignedDeck(playerDTO);

        //assert
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    //deck y all cards tambien devuelven una lista, ¿se testea eso?

    @Test
    public void shouldPlayCardSuccessfully(){}


}