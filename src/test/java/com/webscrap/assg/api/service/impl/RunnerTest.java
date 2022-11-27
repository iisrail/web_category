package com.webscrap.assg.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webscrap.assg.api.model.Category;

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
		service.classify(pages, names);
	}

}
