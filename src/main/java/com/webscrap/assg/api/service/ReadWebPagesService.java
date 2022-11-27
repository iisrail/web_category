package com.webscrap.assg.api.service;

import com.webscrap.assg.api.dto.WebRequestDto;
import com.webscrap.assg.api.dto.PageContentResponseDto;

public interface ReadWebPagesService {
	public PageContentResponseDto pullin(WebRequestDto webRequest);

}
