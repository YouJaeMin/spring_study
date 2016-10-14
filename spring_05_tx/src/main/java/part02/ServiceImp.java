package part02;

public class ServiceImp implements Service {
	private MemDaoImp dao;

	public ServiceImp() {

	}

	public void setDao(MemDaoImp dao) {
		this.dao = dao;
	}

	@Override
	public void insertProcess() {
		dao.insertMethod(new MemDTO(48, "용이", 30, "수원"));
		dao.insertMethod(new MemDTO(49, "대위", 10, "대전"));
	}

}
