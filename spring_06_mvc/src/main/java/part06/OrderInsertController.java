package part06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderInsertController {

	OrderDAO dao;

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/orderInsert.htm", method = RequestMethod.GET)
	public String form() {
		return "view/part06/form";
	}

	@RequestMapping(value = "/orderInsert.htm", method = RequestMethod.POST)
	public String prosess(OrderDTO dto) {
		dao.insertMethod(dto);
		return "redirect:/orderList.htm";
	}
	
	
}
