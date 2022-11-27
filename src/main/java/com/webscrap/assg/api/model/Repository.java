package com.webscrap.assg.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Repository {
	private Map<String,Category> categoriesAll;
	
	public Category getCategory(String value) {
		return categoriesAll.get(value);
	}
	
	public List<Category> getSubCategoryList(List<String> list) {
		List<Category> subCategories = new ArrayList<>();
		
		for(String cat : list) {
			Category category = categoriesAll.get(cat);
			if(category == null) {
				throw new IllegalArgumentException("Not such category " + cat);
			}
			subCategories.add(category);
		}
		return subCategories;
	}

}
