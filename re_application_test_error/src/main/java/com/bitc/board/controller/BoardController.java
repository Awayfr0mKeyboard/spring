package com.bitc.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	

	/**
	 * 페이징 처리 된 게시글 출력 페이지 param : 요청 page, perPageNum
	 */
	// GET : board/list
	@GetMapping("list")
	public String listPage(Criteria cri, Model model, HttpSession session) throws Exception {
		List<BoardVO> list = bs.listCriteria(cri);
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
		String msg = (String) session.getAttribute("msg");
		if (msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg");
		}
		return "board/list";
	}

	/* "게시글 작성 페이지 요청" */
	@GetMapping("write")
	public void write() throws Exception {
	}

	/**
	 * 게시글 등록 요청 처리 추가
	 * 
	 * @throws Exception
	 */
	@PostMapping("write")
	public String write(BoardVO board, HttpSession session) throws Exception {
		// System.out.println(board);
		// logger.info("wirte param : " + board.toString());
		String message = bs.regist(board);
		session.setAttribute("msg", message);
		return "redirect:/board/list";
	}

	/**
	 * board/readPage - 조회수 증가
	 */
	@GetMapping("detail")
	public String detail(int bno, RedirectAttributes rttr) throws Exception {
		bs.updateCnt(bno);
		rttr.addAttribute("bno", bno);
		return "redirect:/board/read";
	}

	// - 상세보기 페이지 출력
	@GetMapping("read")
	public String read(int bno, Model model) throws Exception {
		BoardVO vo = bs.read(bno);
		model.addAttribute("board", vo);
		return "board/read";
	}

	/**
	 * 게시글 수정 페이지 요청 - 게시글 수정 화면 출력
	 */
	// board/update
	@GetMapping("update")
	public String modify(int bno, Model model) throws Exception {
		BoardVO vo = bs.read(bno);
		model.addAttribute(vo);
		return "board/update";
	}

	/**
	 * 게시글 수정 처리 요청 게시글 수정 완료 후 redirect - 수정게시글 상세보기 페이지 이동
	 */
	@PostMapping("update")
	public String modify(BoardVO vo, RedirectAttributes rttr) throws Exception {
		String result = bs.modify(vo);
		rttr.addAttribute("bno", vo.getBno());
		rttr.addFlashAttribute("msg", result);
		return "redirect:/board/read/";
	}

	/**
	 * 게시글 삭제 완료 후 listPage 페이지 로 이동 - redirect
	 */
	@GetMapping("delete")
	public String remove(int bno, RedirectAttributes rttr) throws Exception {
		// logger.info("delete bno : " + bno);
		String msg = bs.remove(bno);
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/board/list";
	}

}
