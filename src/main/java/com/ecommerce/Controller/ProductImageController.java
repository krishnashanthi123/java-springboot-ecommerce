package com.ecommerce.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ProductImageController {
	
	@PostMapping("/upload")
	
	public String uploadImage(@RequestParam("file") MultipartFile file) {
		
		return "Image Uploaded Successfully :" +file.getOriginalFilename();
		
	}

}
