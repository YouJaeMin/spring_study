package part03;

import org.springframework.transaction.annotation.Transactional;

public class ServiceImp implements Service {
	private MemDaoImp dao;

	public ServiceImp() {

	}

	public void setDao(MemDaoImp dao) {
		this.dao = dao;
	}

	@Transactional(rollbackFor = java.lang.Exception.class)
	@Override
	public void insertProcess() {
		dao.insertMethod(new MemDTO(52, "용이", 30, "수원"));
		// dao.insertMethod(new MemDTO(51, "대위", 10, "대전"));
	}

	@Transactional(rollbackFor = java.lang.Exception.class)
	@Override
	public void testProcess() {
		// 트랜잭션이 설정된 메소드에서는 try~catch을 설정하면 안되고
		// 메소드를 호출하는 곳에서 try~catch을 한다.
		try {
			dao.insertMethod(new MemDTO(54, "대위", 10, "대전"));
			dao.insertMethod(new MemDTO(54, "용이", 30, "수원"));
		} catch (Exception e) {
			System.out.println("fff");
		}
	}

}
