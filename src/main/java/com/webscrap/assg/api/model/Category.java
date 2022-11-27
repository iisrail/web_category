package com.webscrap.assg.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter

public class Category {
    private String value;
    private List<String> keywords = new ArrayList<>();
    private List<Phrase> phrases = new ArrayList<>();

    public Category(String value) {
        this.value = value;
    }
    
    public Category keyword(String string) {
    	String[] words =  string.split(",");
    	 
    	for(String word : words) {
    		word = word.trim();
	    	String[] split = word.split("\\s+");
	    	
	    	if(split.length == 1) {
	    		keywords.add(word);
	    	}else {
	    		Phrase phrase = new Phrase(Arrays.asList(split));
	    		phrases.add(phrase);
	    	}
    	}
    	return this;
    }

	@Override
	public String toString() {
		return "Category [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(value, other.value);
	}
	
	
	
}
