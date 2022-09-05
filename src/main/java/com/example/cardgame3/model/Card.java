package com.example.cardgame3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "CARDS")
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "card_id")
    private Integer id;

    @Column(name = "card_name")
    private String cardName;
    @Column(name = "attack_points")
    private Integer attackPoints;
    @Column(name = "defense_points")
    private Integer defensePoints;

    @Column(name = "rarity")
    private String rarity;
    @JsonIgnore
    @ManyToMany(mappedBy = "cardDeck")
    private List<Player> playersWhoOwnCard = new ArrayList<>();


    public Card(String cardName, Integer attackPoints, Integer defensePoints, String rarity) {
        this.cardName = cardName;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.rarity = rarity;
    }

    public void addPlayer(Player player){
        playersWhoOwnCard.add(player);
    }
}
