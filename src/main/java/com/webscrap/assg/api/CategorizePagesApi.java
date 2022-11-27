package com.webscrap.assg.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webscrap.assg.api.dto.CategoryRequestDto;
import com.webscrap.assg.api.dto.CategoryResponseDto;
import com.webscrap.assg.api.dto.PageCategoryResponseDto;
import com.webscrap.assg.api.model.Category;
import com.webscrap.assg.api.model.Repository;
import com.webscrap.assg.api.service.CategoryService;
import com.webscrap.assg.api.service.impl.Runner;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategorizePagesApi {

	
	@Autowired
	private Runner categoryService;
	
	@ApiOperation(value = "/categorize", notes = "Categorizes web pages based on a keyword category", response = CategoryResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server error")})
	@PostMapping(path = "/categorize", consumes = {APPLICATION_JSON_VALUE},produces = {APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<PageCategoryResponseDto> pullIn(@RequestBody CategoryRequestDto webRequest) {
		log.info("Start categorize request {}",  webRequest);		
		List<String> strCategories = webRequest.getCategories();		
		List<String> pages = webRequest.getPages();
		Map<String, List<String>> classify = categoryService.classify(pages, strCategories);
		
        log.info("End categorize");
        return ResponseEntity.ok().body(new PageCategoryResponseDto(classify));
        
    }	
}
