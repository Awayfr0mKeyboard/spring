package com.bitc.common.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDTO {

	private MultipartFile profile;
	private List<MultipartFile> files;
	private String auth;
	private String content;
	
}
