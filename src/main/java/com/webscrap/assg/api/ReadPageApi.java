package com.webscrap.assg.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webscrap.assg.api.dto.WebRequestDto;
import com.webscrap.assg.api.dto.PageContentResponseDto;
import com.webscrap.assg.api.service.ReadWebPagesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReadPageApi {
	@Autowired
	private ReadWebPagesService readWebService;
	
	@ApiOperation(value = "/pullin", notes = "Pull in the text content of the pages", response = PageContentResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server error")})
	@PostMapping(path = "/pullin", consumes = {APPLICATION_JSON_VALUE},produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<PageContentResponseDto> pullIn(@RequestBody WebRequestDto webRequest) {
		log.info("Start pullIn request {}",  webRequest);
		PageContentResponseDto dto = readWebService.pullin(webRequest); 
		
        log.info("End pullIn");
        return ResponseEntity.ok().body(dto);
    }	
}
