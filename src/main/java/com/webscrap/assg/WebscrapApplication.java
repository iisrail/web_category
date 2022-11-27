package com.webscrap.assg;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webscrap.assg.api.model.Category;
import com.webscrap.assg.api.model.Repository;

@SpringBootApplication
public class WebscrapApplication  implements CommandLineRunner {
	@Autowired
	private Repository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebscrapApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String,Category> categoriesAll = new HashMap<>();
		Category starWars = new Category("Star Wars").keyword("star wars, star war, starwars, starwar, r2d2, may the force be with you");
		categoriesAll.put(starWars.getValue(),starWars);
		Category basketball = new Category("Basketball").keyword("basketball, nba, ncaa, lebron james, john stokton, anthony davis");
		categoriesAll.put(basketball.getValue(),basketball);
		Category chess = new Category("Chess").keyword("scheveningen system, swiss system, blitz, garry kasparov, anatoly karpov");
		categoriesAll.put(chess.getValue(),chess);
		Category soccer = new Category("Soccer").keyword("football, kick-off,goal kick,kick off,indirect free kick, diego maradona");
		categoriesAll.put(soccer.getValue(),soccer);
		repository.setCategoriesAll(categoriesAll);
	}



}
