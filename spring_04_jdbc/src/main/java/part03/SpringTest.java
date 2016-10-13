package part03;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("part03/jdbc.xml");

		MemDAO dao = (MemDAO) context.getBean("dao");

//		 dao.insertMethod(new MemDTO("박우우", 20, "청주"));
//		 dao.updateMethod(new MemDTO(18, "홍홍홍"));
		 dao.deleteMethod(19);

		// MemDTO dto = dao.one(21);
		// System.out.printf("%d %s %d %s \n", dto.getNum(), dto.getName(),
		// dto.getAge(), dto.getLoc());

		// System.out.println(dao.countMethod());

		List<MemDTO> list = dao.list();
		for (MemDTO dto : list) {
			System.out.printf("%d %s %d %s \n", dto.getNum(), dto.getName(), dto.getAge(), dto.getLoc());
		}

	}
}
