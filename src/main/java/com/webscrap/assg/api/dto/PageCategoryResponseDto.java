package com.webscrap.assg.api.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
@Getter
public class PageCategoryResponseDto {
	Map<String, List<String>> mapPageCat;
	
	public PageCategoryResponseDto(Map<String, List<String>> mapPageCat) {
		this.mapPageCat = mapPageCat;
	}	

}
