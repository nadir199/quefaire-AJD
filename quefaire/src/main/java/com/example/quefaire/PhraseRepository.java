package com.example.quefaire;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.quefaire.models.Phrase;
import com.example.quefaire.models.User;

public interface PhraseRepository extends CrudRepository<Phrase, Long> {
	@Query(value="SELECT p FROM Phrase p JOIN Like likes ON likes.phrase = p AND likes.user = :user") 
    ArrayList<Phrase> getLikedPhrases(@Param("user") User user);
}