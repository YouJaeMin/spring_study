package part01;

import org.springframework.transaction.support.TransactionTemplate;

public class ServiceImp implements Service {
	private MemDaoImp dao;
	private TransactionTemplate transactionTemplate;

	public ServiceImp() {

	}

	public void setDao(MemDaoImp dao) {
		this.dao = dao;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void insertProcess() {
		dao.insertMethod(new MemDTO(40, "용팔이", 50, "경기"));
		dao.insertMethod(new MemDTO(41, "유대위", 20, "대전"));
	}

}
