package part04_ajax_daum;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// c32f57dcf1110d76d2a223c0a9767b0d - 다음 키
//https://apis.daum.net/search/book?apikey=c32f57dcf1110d76d2a223c0a9767b0d&q=삼국지&output=xml
@Controller
public class SearchDaumController {
	String apikey = "c32f57dcf1110d76d2a223c0a9767b0d";
	String daumUrl = "https://apis.daum.net/search/book?apikey=" + apikey;

	@RequestMapping("/daumForm.do")
	public String form() {
		return "part04_ajax_daum/daumForm";
	}

	// @RequestMapping("/searchOpen.do")
	// public ModelAndView process(String search) throws MalformedURLException,
	// IOException {
	// ModelAndView mav = new ModelAndView();
	// String url =
	// "https://apis.daum.net/search/book?apikey=c32f57dcf1110d76d2a223c0a9767b0d&q="
	// + URLEncoder.encode(search, "UTF-8") + "&output=xml";
	// HttpURLConnection con = (HttpURLConnection) new
	// URL(url).openConnection();
	//
	// BufferedReader reader = new BufferedReader(new
	// InputStreamReader(con.getInputStream(), "UTF-8"));
	// String input = null;
	// String total = "";
	// while ((input=reader.readLine())!= null) {
	// total += input;
	// }
	// mav.addObject("total", total);
	// mav.setViewName("part04_ajax_daum/daumResult");
	// return mav;
	// }

	@RequestMapping("/searchOpen.do")
	public @ResponseBody BookList process(String search) throws MalformedURLException, IOException, JAXBException {

		String url = "https://apis.daum.net/search/book?apikey=c32f57dcf1110d76d2a223c0a9767b0d&q="
				+ URLEncoder.encode(search, "UTF-8") + "&output=xml";
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		
		JAXBContext jaxbContext = null;
		BookList booklist = null;
		
		jaxbContext = JAXBContext.newInstance(BookList.class);
		
		booklist = (BookList) jaxbContext.createUnmarshaller().unmarshal(con.getInputStream());
		
		return booklist;
	}

}
