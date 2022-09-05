package com.example.cardgame3.repository;

import com.example.cardgame3.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICardRepository extends JpaRepository<Card, Integer>, PagingAndSortingRepository<Card, Integer> {


    @Query("select c from Card c")
    List<Card> getAllCardsSaved();

//    @Query("select c from Card c where player_id is null")
//    List<Card> getAvailableCards();



}
