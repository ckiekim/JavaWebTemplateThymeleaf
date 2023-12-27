package com.human.thymeleaf.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.thymeleaf.entity.Board;
import com.human.thymeleaf.entity.Reply;
import com.human.thymeleaf.service.BoardService;
import com.human.thymeleaf.service.ReplyService;
import com.human.thymeleaf.util.JsonUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	private String category = "board";
	@Autowired private BoardService boardService;
	@Autowired private ReplyService replyService;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	@Autowired private JsonUtil jsonUtil;

	@GetMapping("/list")
	public String list(String f, String q, Model model) {
		String field = (f == null || f.equals("")) ? "title" : f;
		String query = (q == null) ? "" : q;
		List<Board> list = boardService.getBoardList(field, query);
//		list.forEach(x -> System.out.println(x));
		
		model.addAttribute("boardList", list);
		model.addAttribute("menu", "list");
		model.addAttribute("category", category);
		return "board/list";
	}
	
	@GetMapping("/write")
	public String writeForm(Model model) {
		model.addAttribute("menu", "write");
		model.addAttribute("category", category);
		return "board/write";
	}
	
	@PostMapping("/write")
	public String writeProc(MultipartHttpServletRequest req) {
		int suid = Integer.parseInt(req.getParameter("suid"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		List<MultipartFile> fileList = req.getFiles("files");
		List<String> list = new ArrayList<>();
		for (MultipartFile part: fileList) {
			if (part.getContentType().contains("octet-stream"))
				continue;
			String filename = part.getOriginalFilename();
			list.add(filename);
			String uploadFile = uploadDir + "upload/" + filename;
			File file = new File(uploadFile);
			try {
				part.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String files = jsonUtil.stringify(list);
		Board board = new Board(suid, title, content, files);
		boardService.insertBoard(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/detail/{bid}/{suid}")
	public String detail(@PathVariable int bid, @PathVariable int suid, String option,
			 				HttpSession session, Model model) {
		int sessSuid = (int) session.getAttribute("sessSuid");
		// 조회수 증가. 단, 본인이 읽거나 댓글 작성후에는 제외.
		if (option == null && sessSuid != suid)
			boardService.increaseViewCount(bid);
		
		Board board = boardService.getBoard(bid);
		model.addAttribute("board", board);
		String jsonFiles = board.getFiles();
		List<String> fileList = jsonUtil.parse(jsonFiles);
		if (fileList != null)
			model.addAttribute("fileList", fileList);
		List<Reply> replyList = replyService.getReplyList(bid);
//		replyList.forEach(x -> System.out.println(x));
		model.addAttribute("replyList", replyList);
		model.addAttribute("count", board.getLikeCount());
		model.addAttribute("menu", "detail");
		model.addAttribute("category", category);
		return "board/detail";
	}
	
	@PostMapping("/reply")
	public String reply(int suid, int bid, String content, HttpSession session) {
		int sessSuid = (int) session.getAttribute("sessSuid");
		int isMine = (suid == sessSuid) ? 1 : 0;
		
		Reply reply = new Reply(sessSuid, bid, content, isMine);
		replyService.insertReply(reply);
		boardService.increaseReplyCount(bid);
		return "redirect:/board/detail/" + bid + "/" + suid + "?option=DNI";	// Do Not Increase
	}
	
	// AJAX 처리
	//	- return 문에 유의
	@GetMapping("/like/{bid}")
	public String like(@PathVariable int bid, Model model) {
		boardService.increaseLikeCount(bid);
		int count = boardService.getBoard(bid).getLikeCount();
		model.addAttribute("count", count);
		return "board/detail::#likeCount";
	}
	
	@GetMapping("/update/{bid}")
	public String updateForm(@PathVariable int bid, HttpSession session, Model model) {
		Board board = boardService.getBoard(bid);
		String jsonFiles = board.getFiles();
		if (jsonFiles != null) {
			List<String> fileList = jsonUtil.parse(jsonFiles);
			session.setAttribute("fileList", fileList);
		}
		model.addAttribute("board", board);
		model.addAttribute("menu", "update");
		model.addAttribute("category", category);
		return "board/update";
	}
	
	@PostMapping("/update")
	public String updateProc(MultipartHttpServletRequest req, HttpSession session) {
		int bid = Integer.parseInt(req.getParameter("bid"));
		int suid = Integer.parseInt(req.getParameter("suid"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		List<String> additionalFileList = (List<String>) session.getAttribute("fileList");
		if (additionalFileList == null)
			additionalFileList = new ArrayList<>();
		String[] delFileList = req.getParameterValues("delFile");
		if (delFileList != null) {
			for (String delName: delFileList) {
				File delFile = new File(uploadDir + "upload/" + delName);
				delFile.delete();
				additionalFileList.remove(delName);
			}
		}
		
		List<MultipartFile> fileList = req.getFiles("files");
		for (MultipartFile part: fileList) {
			if (part.getContentType().contains("octet-stream"))
				continue;
			String filename = part.getOriginalFilename();
			additionalFileList.add(filename);
			String uploadFile = uploadDir + "upload/" + filename;
			File file = new File(uploadFile);
			try {
				part.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		additionalFileList.forEach(x -> System.out.println(x));
		
		String files = jsonUtil.stringify(additionalFileList);
		Board board = new Board(bid, suid, title, content, files);
		boardService.updateBoard(board);
		return "redirect:/board/detail/" + bid + "/" + suid;
	}
	
	@GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid) {
		boardService.deleteBoard(bid);
		return "redirect:/board/list";
	}
	
}
