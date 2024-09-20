package com.bitc.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	/**
	 * file upload 처리 후 업로드된 파일 이름 반환
	 */
	public static String uploadFile(String realPath, MultipartFile file) throws Exception {
		String uploadFileName = "";
		
		UUID uid = UUID.randomUUID(); // 중복 우회를 위한 이름 설정
		String originalName = file.getOriginalFilename();
		// 36개에 - 4개가 추가된 문자열에서 '-' 특수문자 제거한 32개 문자 생성
		String savedName = uid.toString().replace("-", "");
		
		// _를 기준으로 원본파일 이름을 구분할 것이기 때문에 원본 파일에 포함된 _ 특수문자를 공백으로 치환
		uploadFileName = savedName + "_" + (originalName.replace("_", " "));
		
		// 날짜별 디렉토리 생성
		String datePath = calcDataPath(realPath);
		
		File uploadFile = new File(realPath + datePath, uploadFileName);
		System.out.println("uploadFileName : " + uploadFileName);
		
		// MultipartFile.transferTo(File);
		// MultipartFile 객체에 저장된 파일 정보를
		// 매개변수로 전달받은 File 위치로 전송
		file.transferTo(uploadFile);
		
		return makeFileName(datePath, uploadFileName);
	}
	
	// 디렉토리 구분자를 URL 구분자로 변경하여 반환
	private static String makeFileName(String datePath, String uploadFileName) {
		String fileName = datePath + File.separator + uploadFileName;
		// TODO delete
		System.out.println("make File Name : " + fileName);
		return fileName.replace(File.separatorChar, '/');
	}

	//		윈도우 \yyyy\mm\dd 형식의 폴더 생성 및 경로를 문자열로 반환
	//		유닉스 /yyyy/mm/dd
	private static String calcDataPath(String realPath) {
		// 현재 시간에 대한 정보를 저장하고 있는 객체
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
			File.separator + "yyyy" + File.separator + "MM" + File.separator + "dd"
		);
		String datePath = date.format(dtf);
		File file = new File(realPath, datePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		// TODO delete
		System.out.println(datePath);
		return datePath;
	}

	/**
	 * 업로드 경로 와 파일이름을 매개변수로 전달 받아
	 * 지정된 위치에 파일 정보를 byte[] 이진데이터로 반환
	 */
	public static byte[] getBytes(String realPath, String fileName) throws Exception{
		
		File file = new File(realPath, fileName);
		
		InputStream is = new FileInputStream(file);
		/*
		byte[] bytes = new byte[is.available()];
		for(int i=0; i < bytes.length; i++) {
			bytes[i] = (byte)is.read();
		}
		is.close();
		
		return bytes;
		*/
		return StreamUtils.copyToByteArray(is);
	} // getBytes end
	
	// 다운로드 파일 헤더 정보 반환
	public static HttpHeaders getHeaders(String fileName) throws Exception{
		HttpHeaders headers = new HttpHeaders();
		/*
		 *	8비트 / 1byte 단위의 이진 데이터 임을 의미
		 *  옥텟은 컴퓨팅에서 8개의 비트가 하나로 모인것을 의미
		 *  브라우저는 이진데이터는 해석할 수 없으므로 다운로드를 받아 사용자가 처리하도록 데이터를 처리한다. 
		 */
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// headers.set("Content-Type", "application/octet-stream");
		
		// 조합된 이름에서 원본파일 이름 추출
		String origin = fileName.substring( fileName.lastIndexOf("_") + 1 );
		/*
		// URL이 해석할수 있는 인코딩 형식으로 변경
		origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
		// 컨텐츠 배치 옵션
		// attachment : 첨부파일
		// fileName 옵션 - 전송되는 첨부 데이터의 이름 명시
		headers.add("Content-Disposition", "attachment;fileName=\""+origin+"\"");
		*/
		
		ContentDisposition cd = ContentDisposition
								.attachment()
								// 어떤 언어 형식으로 되어 있는 문자열인지 알아야 decoding 할수 있으므로
								// 언어 형식 지정
								.filename(origin, Charset.forName("UTF-8"))
								.build();
		
		headers.setContentDisposition(cd);
		
		return headers;
	}
	
	
}












