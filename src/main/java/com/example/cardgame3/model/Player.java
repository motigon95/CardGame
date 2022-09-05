package com.example.cardgame3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "player_id")
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "owned_cards",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cardDeck = new ArrayList<>();


    public Card playCard(Integer cardNumber){
        Card cardToPlay = this.getCardDeck().get(cardNumber);
        return cardToPlay;
    }

}
