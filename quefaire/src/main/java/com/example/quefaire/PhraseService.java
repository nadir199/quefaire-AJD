package com.example.quefaire;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quefaire.models.Like;
import com.example.quefaire.models.Phrase;
import com.example.quefaire.models.User;
import com.example.quefaire.repository.UserRepository;
import com.example.quefaire.security.services.UserDetailsImpl;

@RestController
@CrossOrigin("http://localhost:4200")
public class PhraseService {
	@Autowired
	private PhraseRepository phraserepo;
	@Autowired
	private LikesRepository likesrepo;
	@Autowired
	private UserRepository userrepo;
	
	@GetMapping("/randomphrase")
	public Phrase getRandomPhrase() {
		long count = phraserepo.count();

		long rand_id = 1 + (long) (Math.random() * (count));
		
		Phrase phrase = phraserepo.findById(rand_id).orElse(null);
		return phrase;
	}
	
	@PostMapping("/addphrase")
	public void CreateNewCar(@RequestBody Phrase p) {
		phraserepo.save(p);
	}
	
	@GetMapping("/phrase/{phraseid}")
	public Phrase getCar(@PathVariable("phraseid") long phraseid) {
		Phrase phrase = phraserepo.findById(phraseid).orElse(null);
		return phrase;
	}
	
	
	@GetMapping("/phrase/{phraseid}/reactions")
	public long getReactions(@PathVariable("phraseid") long phraseid) {
		Phrase p = phraserepo.findById(phraseid).orElse(null);
		return likesrepo.getNumberLikesPerPhraseId(p);
	}
	
	@GetMapping("/phrase/{phraseid}/like")
	public boolean InvertLikePhrase(@PathVariable("phraseid") long phraseId) {
		UserDetailsImpl connectedUserImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Phrase p = phraserepo.findById(phraseId).orElse(null);
		long userId = connectedUserImpl.getId();
		User connectedUser = userrepo.findById(userId).orElse(null);

		int likedStatus = likesrepo.getLikedStatus(p, connectedUser);
		if (likedStatus == 0) { // Not Liked
			if (p!=null) {
				Like l = new Like(p, connectedUser);
				likesrepo.save(l);
			}
			return true; // Liked
		}else { // Liked => Delete the like
			long liked_id = likesrepo.getLikeId(p, connectedUser);
			likesrepo.deleteById(liked_id);
			return false; // Not Liked
		}
	}
	
	@GetMapping("/phrase/{phraseid}/isliked")
	public boolean IsPhraseLiked(@PathVariable("phraseid") long phraseId) {
		UserDetailsImpl connectedUserImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Phrase p = phraserepo.findById(phraseId).orElse(null);
		long userId = connectedUserImpl.getId();
		User connectedUser = userrepo.findById(userId).orElse(null);

		int likedStatus = likesrepo.getLikedStatus(p, connectedUser);
		if (likedStatus == 0)
			return false; // Not Liked
		else
			return true; // Liked
		
	}
	
	@GetMapping("/phrase/likedphrases")
	public ArrayList<Phrase> GetLikedPhrases() {
		UserDetailsImpl connectedUserImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long userId = connectedUserImpl.getId();
		User connectedUser = userrepo.findById(userId).orElse(null);
		return phraserepo.getLikedPhrases(connectedUser);
	}
}
