package part01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/list.do")
	public String listProess(){
		System.out.println("list controller");
		return "part01/list";
	}
	
	@RequestMapping("/write.do")
	public String writeProess(){
		System.out.println("write controller");
		return "part01/write";
	}
	
	@RequestMapping("/view.do")
	public String viewProess(){
		System.out.println("view controller");
		return "part01/view";
	}
	
}
