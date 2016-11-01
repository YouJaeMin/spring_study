package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.PersonDao;
import dto.PersonDTO;

/*
GET(/rest/person/list)     		자료의 조회용
GET(/rest/person/list/3)   		자료의 조회용
DELETE(/rest/person/3)     		자료의 삭제
POST(/rest/person/)+데이터    		자료의 등록
PUT(/rest/person/update)+데이터 	자료의 수정  

@PathVariable-URI의 경로에서 원하는 데이터를 추출하는 용도로 사용
@RequestBody - 전송된 JSON데이터를 객체로 변환해 주는 어노테이션으로 
     @ModeAndView와 유사한 역할을 하지만 JSON에서 사용된다는 점이 차이
*/


@Controller
public class PersonController {

	private PersonDao dao;

	public PersonController() {
	}

	public void setDao(PersonDao dao) {
		this.dao = dao;
	}

	// http://127.0.0.1:8090/rest/person/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<PersonDTO> listMethod() {
		return dao.list();
	}

	// http://127.0.0.1:8090/rest/person/list/1
	@RequestMapping(value = "/list/{ss}", method = RequestMethod.GET)
	public @ResponseBody PersonDTO listMethod(@PathVariable("ss") int num) {
		return dao.list(num);
	}

	// http://127.0.0.1:8090/rest/person/list/1/홍길동
	// @RequestMapping(value = "/list/{num}/{name}", method = RequestMethod.GET)
	// public @ResponseBody PersonDTO listMethod(@PathVariable("num") int num,
	// @PathVariable("name") String name) {
	// PersonDTO dto = new PersonDTO();
	// dto.setNum(num);
	// dto.setName(name);
	// return dao.list(dto);
	// }

	@RequestMapping(value = "/list/{num}/{name}", method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> listMethod(PersonDTO dto) {
		ResponseEntity<PersonDTO> entity = new ResponseEntity<PersonDTO>(dao.list(dto), HttpStatus.OK);
		return entity;
	}

	/*
	  $.ajax({
		   dataType : "text",
		   type : "POST",
		   data : JSON.stringify({"id":"sdf","pass":"123","name":"김김"}),
		   url : "/",
		   success : function(res){}
	   })
	 */
	// http://127.0.0.1:8090/rest/person/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> insertMethod(PersonDTO dto, HttpServletRequest req) {
		
		ResponseEntity<String> entity = null;

		try {
			
			if (dto.getFilename() != null) {
				MultipartFile file = dto.getFilename();
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
					dto.setUpload(random + "_" + fileName);
				}
			}
			
			dao.register(dto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	// http://127.0.0.1:8090/rest/person/update
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> updateMethod(@RequestBody PersonDTO dto) {
		ResponseEntity<String> entity = null;
		try {
			dao.update(dto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// http://127.0.0.1:8090/rest/person/
	@RequestMapping(value = "/{num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMethod(@PathVariable("num") int num) {
		ResponseEntity<String> entity = null;
		try {
			dao.delete(num);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
