package com.example.cardgame3.model;

import lombok.*;

import javax.persistence.*;

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


    public Card(String cardName, Integer attackPoints, Integer defensePoints, String rarity) {
        this.cardName = cardName;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.rarity = rarity;
    }
}
