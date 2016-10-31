package part02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import part02.dto.PersonDTO;

@Controller
public class UserController {
	
	public UserController() {
	}
	
	@RequestMapping("/login.do")
	public String loginProcess(){
		return "part02/loginForm";
	}
	
	@RequestMapping("/logpro.do")
	public String logCheckProcess(PersonDTO dto, HttpSession session, HttpServletRequest request){
		if(dto.getId().equals("kim") && dto.getPass().equals("1234")){
			session.setAttribute("chk", dto.getId());
			if(request.getParameter("returnUrl") != null){
				return "redirect:/"+request.getParameter("returnUrl");
			}
			return "redirect:/index.do";
		}else{
			return "redirect:/login.do";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logoutProcess(HttpSession session){
		session.removeAttribute("chk");
		return "redirect:index.do";
	}
	
}
