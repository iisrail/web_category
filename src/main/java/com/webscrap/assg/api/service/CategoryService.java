package com.webscrap.assg.api.service;

import java.util.List;

import com.webscrap.assg.api.model.Category;

public interface CategoryService {

	void classify(List<String> pages, List<String> categories);

}
