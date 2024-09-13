package com.bitc.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.common.utils.FileUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileController {
	
	private final String uploadDir;
	
	private String saveDir;
	
	/**
	 * Bean Class 생성 후 의존성 주입이 완료되고 나면 
	 * 사용전 마지막에 한번 호출
	 */
	@PostConstruct
	public void fileInit() {
		System.out.println("PostConstruct uploadDir : " + uploadDir);
		File file = new File(uploadDir);
		if(!file.exists()) {
			file.mkdirs();
			System.out.println("Directory 생성 완료");
		}
		System.out.println("FileController 생성 및 사용 준비 완료");
	}
	
	@PostMapping("uploadForm")
	public String uploadForm(MultipartFile file, Model model) throws IOException {
		
		System.out.println("file name : " + file.getOriginalFilename());
		System.out.println("file size : " + file.getSize());
		System.out.println("file type : " + file.getContentType());
		System.out.println("file empty : " + file.isEmpty());
		
		// file 이진 data
		byte[] bytes = file.getBytes(); 
		System.out.println("file bytes length : " + bytes.length);
		
		/*
		// fileInit(); 에서 처리
		String saveDir = "C:\\Temp\\upload";
		File f = new File(saveDir);
		if(!f.exists()) {
			f.mkdirs();
		}
		*/
		
		// 파일 저장 처리
		/*
		// uploadFile(); 에서 처리
		File savedFile = new File(uploadDir, file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(savedFile);
		fos.write(bytes);
		fos.flush();
		fos.close();
		*/
		String uploadFileName = uploadFile(file);
		
		model.addAttribute("savedName", uploadFileName);
		// model.addAttribute("savedName", file.getOriginalFilename());
		return "uploadResult";
	}

	@PostMapping("uploadMultiple")
	public String uploadMultiple(
				MultipartFile[] files,
				Model model
			) throws IOException{
		
		List<String> saves = new ArrayList<>();
		
		for(MultipartFile f : files) {
			String uploadFileName = uploadFile(f);
			saves.add(uploadFileName);
		}
		model.addAttribute("saves", saves);
		
		return "uploadResult";
	}
	
	/**
	 *  <input type="text" name="auth" /> <br/>
		<textarea name="content"></textarea> <br/>
		<input type="file" name="profile" accept="image/*"/> <br/>
		<input type="file" name="files" multiple/> 
	 */
	@PostMapping("uploadWithParam")
	public String uploadWithParam(
				MultipartFile profile,
				List<MultipartFile> files,
				String auth,
				String content,
				Model model
			) throws Exception{
		List<String> saves = new ArrayList<>();
		saves.add(uploadFile(profile));
		for(MultipartFile f : files) {
			saves.add(uploadFile(f));
		}
		model.addAttribute("saves",saves);
		model.addAttribute("auth", auth);
		model.addAttribute("content", content);
		return "uploadResult";
	}
	
	
	
	@GetMapping("downloadFile")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(String fileName) throws Exception{
		System.out.println("downLoadFile : " + fileName);
		return new ResponseEntity<>( 
				FileUtils.getBytes(uploadDir, fileName), // 요청한 파일 데이터를 byte[] 이진 데이터로 반환
				FileUtils.getHeaders(fileName),			 // byte[] 데이터 형식 지정
				HttpStatus.CREATED); // 201 새로운 데이터 생성 정상 실행 응답코드
	}
	
	// 중복되지 않도록 이름을 변경하여 파일을 업로드 한후
	// 실제 업로드된 파일이름 반환
	public String uploadFile(MultipartFile file) throws IOException{
		String savedName = "";
		
		UUID uid = UUID.randomUUID();
		// 32개의 랜덤한 문자 + 4개의 - 특수문자 == 36개의 문자
		String origin = file.getOriginalFilename(); // 원본파일 이름
		String random = uid.toString().replace("-", ""); // - 특수문자 공백으로 치환하여 저장
		savedName = random + "_" + origin;
		
		File uploadFile = new File(uploadDir, savedName);
		// 1번째 매개변수 파일 data를 두번째 매개변수 파일 위치로 복사
		FileCopyUtils.copy(file.getBytes(), uploadFile);
		
		return savedName;
	}
}














