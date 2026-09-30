package com.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtoRequest.TagRequestDto;
import com.ecommerce.dtoResponse.TagResponseDto;
import com.ecommerce.service.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@PostMapping
	
	public TagResponseDto createTag(@RequestBody TagRequestDto dto) {
		
		return tagService.createTag(dto);
	
	}
}
