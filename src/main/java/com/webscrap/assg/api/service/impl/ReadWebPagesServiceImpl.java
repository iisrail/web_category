package com.webscrap.assg.api.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

import com.webscrap.assg.api.dto.WebRequestDto;
import com.webscrap.assg.api.dto.PageContentResponseDto;
import com.webscrap.assg.api.service.ReadWebPagesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadWebPagesServiceImpl implements ReadWebPagesService {

	@Override
	public PageContentResponseDto pullin(WebRequestDto webRequest) {
		Map<String,String> mapPages = new ConcurrentHashMap<>();
		List<String> pages = webRequest.getPathes();
		pages.parallelStream().forEach(p -> parsePage(p,mapPages));
			
		PageContentResponseDto pageContentResponseDto = new PageContentResponseDto(mapPages);
		return pageContentResponseDto;
	}
	
	private void parsePage(String page, Map<String,String> mapPages)  {
		Document doc = null;
		String output = null;
		String errorMsg = null;
		try {			
			doc = Jsoup.connect(page).get();
		} catch (IOException e) {
			errorMsg = e.getMessage();
			log.error("Got exception {} parsing on page {}",errorMsg,page );
			
		}
		if(doc != null) {
			output = Jsoup.clean(doc.toString(), Safelist.simpleText().addTags("p"));
		}else {
			output = errorMsg;
		}	    
		mapPages.put(page, output);
	}

}
