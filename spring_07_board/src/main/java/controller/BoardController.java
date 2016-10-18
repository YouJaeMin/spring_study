package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.PageDTO;
import service.BoardService;

@Controller
public class BoardController {

	private BoardService service;
	private int currentPage;
	private PageDTO pdto;

	public BoardController() {
	}

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/list.sb")
	public ModelAndView listMethod(PageDTO pv) {
		ModelAndView mav = new ModelAndView();

		int totalRecord = service.countProcess();
		if (totalRecord >= 1) {
			if(pv.getCurrentPage() == 0){
				currentPage = 1;
			}else{
				currentPage = pv.getCurrentPage();
			}
			pdto = new PageDTO(currentPage, totalRecord);
			
			mav.addObject("pv", pdto);
			mav.addObject("aList", service.listProcess(pdto));
		}

		mav.setViewName("board/list");
		return mav;
	}

}
