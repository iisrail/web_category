package com.webscrap.assg.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RunnerTest {
	@Autowired
	Runner service;

	@Test
	public void testClassify() {
		List<String> pages = new ArrayList<>();
		List<String> names = new ArrayList<>();
		pages.add("http://www.starwars.com");
		pages.add("https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm");
		pages.add("https://edition.cnn.com/sport");
		names.add("Star Wars");
		names.add("Basketball");
		Map<String, List<String>> map = service.classify(pages, names);
		assertFalse(map.get("http://www.starwars.com").contains("Basketball"));
		assertTrue(map.get("https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm").contains("Star Wars"));
		assertTrue(map.get("https://edition.cnn.com/sport").contains("Basketball"));

	}

}
