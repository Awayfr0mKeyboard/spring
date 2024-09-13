package com.bitc.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StreamUtils;

public class FileUtils {
	
	/**
	 * 업로드 경로 외 파일 이름을 매개변수로 전달 받아
	 * 지정된 위치에 파일 정보를 byte[] 이진데이터로 반환
	 */
	public static byte[] getBytes(String realPath, String fileName) throws Exception {
		
		File file = new File(realPath, fileName);

		InputStream is = new FileInputStream(file);
		
		/*
		byte[] bytes = new byte[is.available()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)is.read();
		}
		is.close();
		
		return bytes;
		*/
		
		return StreamUtils.copyToByteArray(is);
	}
	
	// 다운로드 파일 헤더 정보 반환
	public static HttpHeaders getHeaders(String fileName) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		
		/**
		 * 8비트 / 1byte 단위의 이진 데이터임을 의미
		 * 옥텟은 컴퓨팅에서 8개의 비트가 하나로 모인 것을 의미
		 * 브라우저는 이진데이터는 해석할 수 없으므로 다운르드를 받아 사용자가 처리하도록 데이터를 처리한다.
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// headers.set("Content-Type", "application/octet-stream");
		 */
		// 조합된 이름에서 원본 파일 이름 추출
		String origin = fileName.substring(fileName.lastIndexOf("_") + 1);
		/*
		// URL이 해석할 수 있는 인코딩 형식으로 변경
		origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
		// 컨텐츠 배치 옵션
		// attachment : 첨부 파일
		// fileName 옵션 - 전송되는 첨부 데이터의 이름 명시
		headers.add("Content-dispositon", "attachment;fileName=\""+origin+"\"");
		*/
		
		ContentDisposition cd = ContentDisposition
				.attachment()
				// 어떤 언어 형식으로 되어있는 문자열인지 알아야 decoding 할 수 있으므로
				// 언어 형식 지정
				.filename(origin, Charset.forName("UTF-8"))
				.build();
		
		headers.setContentDisposition(cd);
		
		return headers;
	}
	
}
