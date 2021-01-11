package com.example.quefaire.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.quefaire.CategoryRepository;
import com.example.quefaire.PhraseRepository;
import com.example.quefaire.repository.RoleRepository;
import com.example.quefaire.repository.UserRepository;


@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	PhraseRepository phraserepo;
	@Autowired
	CategoryRepository categrepo;
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	RoleRepository rolerepo;

	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		loadCategoriesData();
		loadPhrasesData();
	}
	
	private void loadCategoriesData() {
		Category p1 = new Category("Extérieur");
		Category p2 = new Category("Intérieur");
		Category p3 = new Category("Cuisine");
		Category p4 = new Category("Sport");
		Category p5 = new Category("Jeu");

		ArrayList<Category> v = new ArrayList<Category>();
		v.add(p1);
		v.add(p2);
		v.add(p3);
		v.add(p4);
		v.add(p5);

		categrepo.saveAll(v);
	}
	
	private void loadPhrasesData() {
		Category c = categrepo.findById(1L).orElse(null);
		Category c1 = categrepo.findById(2L).orElse(null);
		Category c2 = categrepo.findById(5L).orElse(null);


		Phrase p1 = new Phrase("Faire un footing", "Courir pour une bonne demi heure, rien de mieux pour bien commencer la journée", c);
		Phrase p2 = new Phrase("Faire le tour du quartier en vélo", "", c);
		Phrase p3 = new Phrase("Faire un tour à la piscine", "", c);
		Phrase p4 = new Phrase("Préparer un bon plat", "", c1);
		Phrase p5 = new Phrase("Jouer au monopoly", "", c2);

		ArrayList<Phrase> v = new ArrayList<Phrase>();
		v.add(p1);
		v.add(p2);
		v.add(p3);
		v.add(p4);
		v.add(p5);
		phraserepo.saveAll(v);
				
		Role r1 = new Role(ERole.ROLE_USER);
		Role r2 = new Role(ERole.ROLE_MODERATOR);
		Role r3 = new Role(ERole.ROLE_ADMIN);
		
		rolerepo.save(r1);
		rolerepo.save(r2);
		rolerepo.save(r3);
		
		User user = new User("admin", 
				 "admin@quefaire.fr",
				 encoder.encode("Admin123456!"));

		Set<String> strRoles = new HashSet<String>();
		strRoles.add("admin");
		strRoles.add("user");
		Set<Role> roles = new HashSet<>();
		

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = rolerepo.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);
		
				break;
			case "mod":
				Role modRole = rolerepo.findByName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(modRole);
		
				break;
			default:
				Role userRole = rolerepo.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
		});
		
		
		user.setRoles(roles);
		userrepo.save(user);
	}
}
