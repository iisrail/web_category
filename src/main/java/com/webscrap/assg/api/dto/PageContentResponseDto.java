package com.webscrap.assg.api.dto;

import java.util.Map;

import lombok.Getter;
@Getter
public class PageContentResponseDto {
	Map<String, String> mapPageText;
	
	public PageContentResponseDto(Map<String, String> mapPageText) {
		this.mapPageText = mapPageText;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		mapPageText.forEach((k, v) -> buffer.append("URL: " + k + "\n\nCONTENT: " + v + "\n\n"));
		return "PageContentResponseDto [mapPageText\n" + buffer + "]";
	}
	
	

}
