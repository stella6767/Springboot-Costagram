package com.example.costagram.web.dto.image;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageReqDto {
	private MultipartFile file;
	private String tag;
	
	
}
