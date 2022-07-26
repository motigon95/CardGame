package com.example.cardgame3.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PLAYERS")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer id;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_health")
    private Integer playerHealth = 10;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "player_id")
    private List<Card> cardDeck = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
//    private Card card;


    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player(String playerName, List<Card> cardDeck) {
        this.playerName = playerName;
        this.cardDeck = cardDeck;
    }

    public Card playCard(Integer cardNumber){
        Card cardToPlay = this.getCardDeck().get(cardNumber);
        return cardToPlay;
    }

}
