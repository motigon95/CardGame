package com.example.cardgame3.service;

import com.example.cardgame3.dto.CardDTO;
import com.example.cardgame3.dto.PlayerDTO;
import com.example.cardgame3.model.Card;
import com.example.cardgame3.repository.ICardRepository;
import com.example.cardgame3.repository.IPlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CardGameServiceTest {

    @Mock
    ICardRepository cardRepository;

    @Mock
    IPlayerRepository playerRepository;

    @Spy
    ModelMapper modelMapper;

    @InjectMocks
    CardGameService cardGameService;

    @Test
    public void shouldSaveCardSuccessfully() {
        //arrange
        CardDTO cardDTO = new CardDTO("Lote", 1, 1, "rare");
        //act
        ResponseEntity<?> expectedResponse = cardGameService.saveCard(cardDTO);
        //assert
        assertEquals(HttpStatus.OK, expectedResponse.getStatusCode());
    }

    @Test
    public void shouldSavePlayerSuccessfully(){
        //arrange
        PlayerDTO playerDTO = new PlayerDTO("Note");
        //act
        ResponseEntity<?> expectedResponse = cardGameService.savePlayer(playerDTO);
        //assert
        assertEquals(HttpStatus.OK, expectedResponse.getStatusCode());
    }
}
