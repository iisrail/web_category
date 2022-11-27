package com.webscrap.assg.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.webscrap.assg.api.dto.WebRequestDto;
import com.webscrap.assg.api.dto.PageContentResponseDto;
import com.webscrap.assg.api.model.Category;
import com.webscrap.assg.api.model.Phrase;
import com.webscrap.assg.api.model.Repository;
import com.webscrap.assg.api.service.ReadWebPagesService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Getter
public class Runner {
	@Autowired
	Repository repository;

	@Autowired
	ReadWebPagesService pageService;

	public Map<String, List<String>> classify(List<String> pages, List<String> names) {
		log.info("Start classify for finding \ncategories: {} for these pages\n {}", names, pages);
		Map<String, List<String>> answerMap = new HashMap<>();		
		// convert strings to categories
		List<Category> subCategoryList = repository.getSubCategoryList(names);
		WebRequestDto dto = new WebRequestDto();
		dto.setPathes(pages);
		PageContentResponseDto responseDto = pageService.pullin(dto);
		Map<String, String> mapPageText = responseDto.getMapPageText();
		for (Map.Entry<String, String> entry : mapPageText.entrySet()) {
			
			String textPage = entry.getValue();
			List<Category> foundCat = findCategoriesInText(textPage, subCategoryList);
			if (foundCat.isEmpty()) {
				log.info("classify for page {} not found any from these list categories {}", entry.getKey(),
						subCategoryList);				
			} else {
				log.info("classify for page {} found the following categories {}", entry.getKey(), foundCat);	
				
			}
			List<String> catNames = foundCat.stream().map(Category:: getValue).collect(Collectors.toList());
			answerMap.put(entry.getKey(), catNames);
		}		
		log.info("End classify ");
		return answerMap;
	}

	public List<Category> findCategoriesInText(String text, List<Category> categories) {
		log.info("Start findCategoriesInText, categories:{}", categories);
		List<Category> foundCategories = new ArrayList<>();

		Map<String, List<Integer>> wordMap = initMap(text);
		categories.stream()
			.filter(cat->isCategoryInMap(wordMap, cat))
			.forEach(foundCategories::add);
		log.info("End findCategoriesInText, found categories", foundCategories);
		return foundCategories;
	}
	
	/*
	 * Put the text from a page to the map, word is a key and value will be the list of its locations in text
	 */
	private Map<String, List<Integer>> initMap(String text) {
		// convert all text to lower case
		text = text.toLowerCase();
		// split text string to separated words by delimiters space,comma and dot,
		Iterable<String> words = Splitter.on(Pattern.compile("[[.,\\s]]")).split(text);
		Map<String, List<Integer>> wordMap = new HashMap<>();
		int counter = 0;
		for (String word : words) {				
			List<Integer> places = wordMap.get(word);
			if (places == null) {
				places = new ArrayList<>();
			}
			places.add(counter++);
			wordMap.put(word, places);
		}
		return wordMap;
	}

	private boolean isCategoryInMap(Map<String, List<Integer>> wordMap, Category category) {
		// first of all look for keyword and then for phrase
		if (isKeywordInMap(wordMap, category)) {
			return true;
		}

		if (isPhraseInMap(wordMap, category)) {
			return true;
		}
		return false;
	}

	private boolean isKeywordInMap(Map<String, List<Integer>> wordMap, Category category) {
		log.info("Start isKeywordInMap for category {} ", category);
		for (String keyword : category.getKeywords()) {
			if (wordMap.containsKey(keyword)) {
				log.info("found keyword {} ", keyword);
				return true;
			}
			
		}
		log.info("End isKeywordInMap for category {}  not found", category);
		return false;
	}

	private boolean isPhraseInMap(Map<String, List<Integer>> wordMap, Category category) {
		log.info("Start isPhraseInMap for category {} ", category);
		for (Phrase phrase : category.getPhrases()) {			
			log.info("Checking phrase ' {} ' ", phrase);
			if (isPhrase(wordMap, phrase)) {
				log.info("The phrase ' {} ' was found", phrase);
				return true;
			}
			log.info("The phrase ' {} ' was not found", phrase);

		}
		log.info("End isPhraseInMap for category {} ", category);
		return false;
	}

	private boolean isPhrase(Map<String, List<Integer>> wordMap, Phrase phrase) {
		log.info("Start isPhrase for phrase {} ", phrase);
		List<String> words = phrase.getWords();
		String curWord = words.get(0);
		List<Integer> locations = null;
		List<Integer> intersected = wordMap.get(curWord);
		if (intersected == null) {
			log.info("Not found the first word:  {}", curWord);
			return false;
		}
		for (int i = 1; i < words.size(); i++) {
			curWord = words.get(i);
			locations = wordMap.get(curWord);
			if (locations == null) {
				log.info("the word {} was not found ", curWord);
				return false;
			}
			intersected = findIntersections(intersected, locations);
			if(intersected.isEmpty()) {
				log.info("the continue for the phrase was not found on word {}", curWord);
				return false;
			}
		}
		
		log.info("End isPhrase");
		return !intersected.isEmpty();
	}

	private List<Integer> findIntersections(List<Integer> list1, List<Integer> list2) {
		log.info("Start findIntersections for list1 {}, list2 {} ", list1, list2);
		List<Integer> intersected = new ArrayList<>();
		for (int index1 : list1) {
			for (int index2 : list2) {
				if (index2 == index1 + 1) {
					intersected.add(index2);
				}
			}
		}
		log.info("End findIntersections {} ", intersected);
		return intersected;

	}

}
