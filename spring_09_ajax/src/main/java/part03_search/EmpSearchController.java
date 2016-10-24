package part03_search;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmpSearchController {
	EmpDAO dao;
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/search.do")
	public String searchForm(){
		return "part03_search/searchForm";
	}
	
	// json 으로 리턴
	@RequestMapping("/process.do")
	public @ResponseBody List<EmployeeDTO> searchProcess(String data){
		return dao.search(data);
	}
	
}
