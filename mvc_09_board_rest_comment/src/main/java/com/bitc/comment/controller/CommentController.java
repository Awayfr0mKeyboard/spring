package com.bitc.comment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitc.comment.service.CommentService;
import com.bitc.comment.vo.CommentVO;
import com.bitc.common.util.Criteria;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService cs;
	
	// /comments
	@PostMapping("")
//	@ResponseBody
	public ResponseEntity<String> addComment(
			 // @RequestParam("commentAuth") String cAuth, String commentText, int bno
			CommentVO comment
			) {
		HttpHeaders headers = new HttpHeaders();
		// MediaType type = new MediaType("application","json",Charset.forName("utf-8"));
		// application/json;charset=utf-8
		// headers.setContentType(type);
		headers.set("Content-Type","text/plain;charset=utf-8");

		ResponseEntity<String> entity = null;
		try {
			String message = cs.addComment(comment);
			entity = new ResponseEntity<>( message , headers , HttpStatus.OK); // 200
		} catch (Exception e) {
			entity = new ResponseEntity<>(
					e.getMessage(),
					headers,
			// 		HttpStatus.INTERNAL_SERVER_ERROR
					HttpStatus.BAD_REQUEST
			);
		}
		return entity;
	} // add comment end
	
	// ${path}/comments/all/"+bno  GET
	// /comments
	@GetMapping("/all/{bno}")
	public ResponseEntity<List<CommentVO>> list(
				@PathVariable(name = "bno") int bno
			){
		ResponseEntity<List<CommentVO>> entity = null;
		
		try {
			List<CommentVO> list = cs.commentList(bno);
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	/**
	 * 수정 요청 처리
	 * "${path}/comments/"+cno
	 */
	@PutMapping("/{cno}")
	public ResponseEntity<String> update(
			@PathVariable int cno,
			@RequestBody CommentVO vo
			){
		ResponseEntity<String> entity = null;
		vo.setCno(cno);
		
		try {
			String result = cs.updateComment(vo); // 댓글 수정 요청
			entity = new ResponseEntity<>(result,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/**
	 * 삭제 요청 처리
	 * "${path}/comments/"+cno
	 */
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> delete(
				@PathVariable(name = "cno") int cno
			){
		
		ResponseEntity<String> entity = null;
		
		try {
			String result = cs.deleteComment(cno);
			entity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/**
	 * 페이징 처리된 댓글 목록
	 * ${path}/comments/"+bno+"/"+page
	 * return - List<CommentVO>요청한 페이지 댓글 목록, PageMaker : 이동할 페이지 정보
	 */
	@GetMapping("/{bno}/{page}")
	public ResponseEntity<Map<String, Object>> listPage(
				@PathVariable(name="bno") int bno,
				@PathVariable(name="page") int page
			){
		ResponseEntity<Map<String, Object>> entity = null;
		
		Criteria cri = new Criteria(page, 5);
		
		try {
			Map<String, Object> map = cs.commentPage(cri, bno);
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}











