package part02_daoSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("part02_daoSupport/jdbc.xml");

		MemDAO dao = (MemDAO) context.getBean("dao");

		// dao.insertMethod(new MemDTO("박나리", 40, "서울"));
		// dao.updateMethod(new MemDTO(22, "나뤼쿠"));
		// dao.deleteMethod(22);

		// MemDTO dto = dao.one(21);
		// System.out.printf("%d %s %d %s \n", dto.getNum(), dto.getName(), dto.getAge(), dto.getLoc());

		System.out.println(dao.countMethod());
		
		// List<MemDTO> list = dao.list();
		// for (MemDTO dto : list) {
		// System.out.printf("%d %s %d %s \n", dto.getNum(), dto.getName(),
		// dto.getAge(), dto.getLoc());
		// }

	}
}
