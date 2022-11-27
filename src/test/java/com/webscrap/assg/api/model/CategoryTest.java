package com.webscrap.assg.api.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {
	Category category;

	@BeforeEach
	void setUp() {
		category = new Category("Star War");
	}

	@Test
	public void testKeyword() {
		category = category.keyword("star war, starwars, starwar, r2d2, may the force be with you");
		System.out.println("Test Category " + category);
		List<String> expKeywords = new ArrayList<>();
		expKeywords.add("starwars");
		expKeywords.add("starwar");
		expKeywords.add("r2d2");
		assertArrayEquals(expKeywords.toArray(),category.getKeywords().toArray());
	}

}
