package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dtoRequest.TagRequestDto;
import com.ecommerce.dtoResponse.TagResponseDto;
import com.ecommerce.entity.Tags;
import com.ecommerce.repository.TagRepository;



@Service
public class TagService {
	
	@Autowired
	
	private TagRepository tagRepository;
	
	public TagResponseDto createTag(TagRequestDto dto) {
		
		 Optional<Tags> existingTag =
	                tagRepository.findByTagName(dto.getTagName());

	        if (existingTag.isPresent()) {
				/*
				 * throw new RuntimeException(
				 * 
				 * "Tag already exists: " + dto.getTagName());
				 */
	        	
	        	TagResponseDto response = new TagResponseDto();
	            response.setTagId(existingTag.get().getTagId());
	            response.setTagName(existingTag.get().getTagName());
	            return response;
	        }
		
		
		
		
		Tags tag =new  Tags();
		tag.setTagName(dto.getTagName());
		
		Tags savedTag= tagRepository.save(tag);
		
		TagResponseDto response=new TagResponseDto();
		
		response.setTagId(savedTag.getTagId());
		
		response.setTagName(savedTag.getTagName());
		
		return response;
		
	}
	  // GET TAG BY ID (USING orElseThrow - YOUR REQUESTED CHANGE)
    public TagResponseDto getTagById(Integer tagId) {

        Tags tag = tagRepository.findById(tagId)
                .orElseThrow(() ->
                        new RuntimeException("Tag Not Found with id: " + tagId));

        TagResponseDto response = new TagResponseDto();
        response.setTagId(tag.getTagId());
        response.setTagName(tag.getTagName());

        return response;
    }

}
