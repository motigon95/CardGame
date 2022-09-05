package com.example.cardgame3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CardDTO {

    private String cardName;
    private Integer attackPoints;
    private Integer defensePoints;
    private String rarity;

}
