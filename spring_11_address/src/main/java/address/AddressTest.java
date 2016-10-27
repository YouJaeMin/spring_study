package address;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressTest {
	
	@RequestMapping("/addrtest.do")
	public String adder(){
		return "view/address";
	}
	
}
