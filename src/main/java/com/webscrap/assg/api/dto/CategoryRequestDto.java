package com.webscrap.assg.api.dto;

import java.util.List;

import lombok.Data;
@Data
public class CategoryRequestDto {
	List<String> pages;
	List<String> categories;
	
}
