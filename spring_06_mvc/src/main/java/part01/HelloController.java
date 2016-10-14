package part01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/hello.htm")
	public String search(){
		// jsp 페이지는 web-inf폴더에넣는다. 보안이유
		return "view/part01/hello";
	}
	
	
}
