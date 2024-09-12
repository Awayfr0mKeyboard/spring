package com.bitc.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitc.board.service.BoardService;
import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.Criteria;
import com.bitc.common.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;

	/* "게시글 작성 페이지 요청" */
	// @RequestMapping(value="/register",method=RequestMethod.GET)
	// board/register
	@GetMapping("register")
	public void register() throws Exception {
		// /board/register
		// WEB-INF/views/board/register.jsp
		System.out.println("게시글 작성 페이지 요청");
	}

	/**
	 * 게시글 등록 요청 처리 추가
	 * @throws Exception 
	 */
	@PostMapping("register")
	public String register(
					BoardVO board
					// ,HttpServletRequest request
					// , Model model
					,HttpSession session
				) throws Exception {
		/*
		request.setCharacterEncoding("UTF-8");
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content")); 
		board.setWriter(request.getParameter("writer"));
		*/
		System.out.println(board);
		String message = bs.regist(board);
		System.out.println(message);
		session.setAttribute("msg", message);
		return "redirect:/board/listPage";
	}

	/**
	 * 게시글 상세보기 요청 처리 - 게시글 번호
	 */
	// board/readPage - 조회수 증가
	@GetMapping("readPage")
	public String readPage(
			int bno // 상세보기 요청한 게시글 번호 
			, 
			// 리디렉션 으로 화면 전환시 파라미터 또는 속성 값을 전달하는 객체 
			RedirectAttributes rttr 
			) throws Exception{
		bs.updateCnt(bno);
		rttr.addAttribute("bno",bno);
		// return "redirect:/board/read?bno="+bno;
		return "redirect:/board/read";
	}
	
	// - 상세보기 페이지 출력
	@GetMapping("read")
	public String read(int bno, Model model)throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute(vo);// boardVO
		return "board/read";
	}

	/**
	 * 게시글 수정 페이지 요청 - 게시글 수정 화면 출력
	 */
	// board/modify
	@GetMapping("modify")
	public String modify(int bno, Model model) throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute(vo);
		return "board/modify";
	}

	/**
	 * 게시글 수정 처리 요청 게시글 수정 완료 후 redirect - 수정게시글 상세보기 페이지 이동
	 */
	@PostMapping("modify")
	public String modify(BoardVO vo,RedirectAttributes rttr) throws Exception{
		String result = bs.modify(vo);
		rttr.addAttribute("bno", vo.getBno());
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/read";
	}

	/**
	 * 게시글 삭제 완료 후 listPage 페이지 로 이동 - redirect
	 */
	@GetMapping("remove")
	public String remove(int bno, RedirectAttributes rttr) throws Exception{
		String msg = bs.remove(bno);
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/board/listPage";
	}

	/**
	 * 페이징 처리 된 게시글 출력 페이지 param : 요청 page, perPageNum
	 */
	// GET : board/listPage
	@GetMapping("listPage")
	// 사용자가 요청한 페이지 번호에 따라서 페이징 처리된 리스트 출력
	public void listPage(
				Criteria cri, Model model,
				HttpSession session
				) throws Exception{
		// WEB-INF/views/board/listPage.jsp
		List<BoardVO> list = bs.listCriteria(cri);
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
		String msg = (String)session.getAttribute("msg");
		if(msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg");
		}
	}
	

}














