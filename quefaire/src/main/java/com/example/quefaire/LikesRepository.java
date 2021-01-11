package com.example.quefaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quefaire.models.Like;
import com.example.quefaire.models.Phrase;
import com.example.quefaire.models.User;

public interface LikesRepository extends CrudRepository<Like, Long> {
	@Query("SELECT count(*) FROM Like likes where likes.phrase = :ph") 
    long getNumberLikesPerPhraseId(@Param("ph") Phrase ph);
	
	@Query("SELECT count(*) FROM Like likes where likes.phrase = :phrase and likes.user = :user") 
    int getLikedStatus(@Param("phrase") Phrase phrase, @Param("user") User user);
	
	@Query("SELECT id FROM Like likes where likes.phrase = :phrase and likes.user = :user") 
    long getLikeId(@Param("phrase") Phrase phrase, @Param("user") User user);
}