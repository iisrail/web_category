package com.webscrap.assg.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webscrap.assg.api.dto.WebRequestDto;
import com.webscrap.assg.api.dto.PageContentResponseDto;
import com.webscrap.assg.api.service.ReadWebPagesService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class ReadWebPagesServiceImplTest {
	private static final String[] pathes = {
			"http://www.msn.com/en-nz/travel/tripideas/70-of-the-planets-most-breathtaking-sights/ss-AAIUpDp",
			"https://www.radiosport.co.nz/sport-news/rugby/accident-or-one-last-dig-eddie-jones-reveals-hansens-next-job/",
			"https://www.glamour.de/frisuren/frisurenberatung/haarschnitte",
			"https://www.bbc.com",
			"https://www3.forbes.com/business/2020-upcoming-hottest-new-vehicles/13/?nowelcome",
			"https://www.tvblog.it/post/1681999/valerio-fabrizio-salvatori-gli-inseparabili-chi-sono-pechino-express-2020",
			"http://edition.cnn.com/"	
	};
	
	@Autowired
	ReadWebPagesService service;
	
	
	@Test
	public void pullinPage_test() throws IOException {
		URL url = new URL("https://www.radiosport.co.nz/sport-news/rugby/accident-or-one-last-dig-eddie-jones-reveals-hansens-next-job/");
		org.jsoup.nodes.Document doc = Jsoup.parse(url, 3*1000);
		
	    String cleanHtml = Jsoup.clean(doc.toString(), Safelist.simpleText().addTags("p"));
		log.info(cleanHtml);
	}
	
	@Test
	public void pullinListPages_test() throws IOException {
		
		WebRequestDto dto = new WebRequestDto();
		List<String> pathesList = Arrays.asList(pathes); 
		dto.setPathes(pathesList);
		PageContentResponseDto responseDto = service.pullin(dto);
		assertNotNull(responseDto);
		log.info(responseDto.toString());
	}
}
