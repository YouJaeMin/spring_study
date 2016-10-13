package part01;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
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
		Object result = transactionTemplate.execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				try{
					dao.insertMethod(new MemDTO(43, "용팔이", 30, "수원"));
					dao.insertMethod(new MemDTO(43, "유대위", 10, "대전"));
					return "ok";
				}catch (Exception e) {
					status.setRollbackOnly();
					return "fail";
				}
				
			}
		});
		
		System.out.println("result : " + result);
		
		
	}

}
