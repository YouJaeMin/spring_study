package part04_ajax_daum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// c32f57dcf1110d76d2a223c0a9767b0d - 다음 키

@Controller
public class SearchDaumController {
	
	@RequestMapping("/daumForm.do")
	public String form(){
		return "part04_ajax_daum/daumForm";
	}
}
