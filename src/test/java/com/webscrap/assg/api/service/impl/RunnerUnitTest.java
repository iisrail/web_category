package com.webscrap.assg.api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.common.base.Splitter;
import com.webscrap.assg.api.model.Category;

public class RunnerUnitTest {
	
	private Runner service;
	private static Category starWars;
	private static Category basketball;
	private static Category soccer;
	private static Category chess;
	
	private static final String textStar = "StarWars.com | The Official Star Wars Website Skip Navigation may force be with you "
			+ "Last Jedi - Get it on Digital HD films Solo: A Star Wars Story - Get it on Digital HD films Show More Loading...\n<p>Supplemental may force be "
			+ "Privacy Policy for Malaysia</p> Star Wars Philippines Star Wars: The Rise of Skywalker #StarWarsPH films Star Wars Virtual Run: "
			+ "Are you ready for this epic event? Register Now events Star Wars: MAY THE FORCE BE WITH YOU The Last Jedi - Get it on Digital HD films Solo: A Star Wars Story - "
			+ "Get it on LEBRON JAMES films Show More ANTHONY Privacy Policy for Philippines</p> Andor: Episode 12 \"Rix Road\" - "
			+ "Now Streaming Tony Gilroy DAVIS Breaks Down Andor Season 1 10 Easter Eggs We Found in Luthen’s Gallery  Tony Gilroy Breaks Down Andor Season 1  "
			+ " Quiz: Which Andor Character Are You?   Star Wars: The High Republic Chronological Reader's Guide   New Phase II Concepts and Covers Revealed "
			+ " Latest News &amp; Blog Latest News &amp; Blog // Latest Video";
	
	private static final String textChess = " Garry Kasparov was the eight of the twelve World Chess Champions whom Frederic met. In our new weekly series he tells us how he met and befriended the top players, and the adventures they experienced together. Frederic has written a new book, together with Professor Christian Hesse, with fascinating chess stories from the last 50 years. It appeared (first in German) in October.";

    @BeforeAll
    public static void setup() {
		starWars = new Category("Star Wars").keyword("star war, starwars, starwar, r2d2, may the force be with you");
		basketball = new Category("Basketball").keyword("basketball, nba, ncaa, lebron james, john stokton, anthony davis");
		soccer = new Category("Soccer").keyword("football, kick-off,goal kick,kick off,indirect free kick, diego armando maradona");
		chess = new Category("Chess").keyword("scheveningen system, swiss system, blitz, garry kasparov, anatoly karpov");
    }
	
	@BeforeEach
	void setUp() {
		service = new Runner();
	}

	@Test
	public void findCategoriesInText_haveToFindStarWarsAndBasketball() {
		List<Category> categories = new ArrayList<>();
		categories.add(basketball);
		categories.add(starWars);
		categories.add(soccer);
		List<Category> findCategoriesInText = service.findCategoriesInText(textStar, categories);
		System.out.println(findCategoriesInText);
		assertThat(findCategoriesInText.contains(basketball));
		assertThat(findCategoriesInText.contains(starWars));
		assertThat(!findCategoriesInText.contains(soccer));
	}
	
	@Test
	public void findCategoriesInText_haveToFindChess() {
		List<Category> categories = new ArrayList<>();
		categories.add(chess);
		categories.add(soccer);
		List<Category> findCategoriesInText = service.findCategoriesInText(textChess, categories);
		System.out.println(findCategoriesInText);
		assertThat(findCategoriesInText.contains(chess));
		assertThat(!findCategoriesInText.contains(soccer));

	}
	
	@Disabled
	@Test
	public void testSplit() {
		String text = "Last Jedi - Get it, on;Digital <p>HD films Solo:Star Wars Story - Get";
		Iterable<String> words = Splitter.on(Pattern.compile("[.,;:\\s]")).split(text);
		words.forEach(w->System.out.print(w + " "));
	}

}
