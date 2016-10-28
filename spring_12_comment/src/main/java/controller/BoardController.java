package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.ReplyDTO;
import service.BoardService;

@Controller
public class BoardController {

	private BoardService service;

	public BoardController() {
	}

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/boardList.do")
	public ModelAndView boardListPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.boardListProcess());
		mav.setViewName("boardList");
		return mav;
	}

	@RequestMapping("/boardView.do")
	public ModelAndView boardViewPage(int bno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO", service.boardViewProcess(bno));
		// mav.setViewName("boardView");
		mav.setViewName("boardView2");
		return mav;
	}

	@RequestMapping("/replyInsertList.do")
	public @ResponseBody List<ReplyDTO> replyListPage(ReplyDTO rdto, HttpServletRequest req) {

		if (rdto.getFilename() != null) {
			MultipartFile file = rdto.getFilename();
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				UUID random = UUID.randomUUID();
				String root = req.getSession().getServletContext().getRealPath("/");
				String saveDirectory = root + "temp" + File.separator;

				File fe = new File(saveDirectory);
				if (!fe.exists()) {
					fe.mkdir();
				}
				fe = new File(saveDirectory, random + "_" + fileName);

				try {
					FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fe));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				rdto.setRupload(random + "_" + fileName);

			}
		}
		return service.replyListProcess(rdto);
	}

	@RequestMapping("/replyDelete.do")
	public @ResponseBody List<ReplyDTO> replyDeleteListPage(ReplyDTO rdto, HttpServletRequest request) {
		return service.replyDeleteProcess(rdto, request);
	}

	@RequestMapping("/replyUpdate.do")
	public @ResponseBody List<ReplyDTO> replyUpdateListPage(ReplyDTO rdto, HttpServletRequest request) {
		return service.replyUpdateProcess(rdto, request);
	}
	
	@RequestMapping("/download.do")
	public ModelAndView replyDownLoad(int rno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rno", rno);
		mav.setViewName("download");
		return mav;
	}

}
