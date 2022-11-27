package com.webscrap.assg.api.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class Phrase {
	private final List<String> words;
	
	public Phrase(List<String> words) {
		this.words = words;
	}
	
	public Phrase(String phrase) {
		this.words = Arrays.asList(phrase.split("\\s+"));
	}

	@Override
	public String toString() {
		return "Phrase [words=" + words + "]";
	}
	
	
	
}
