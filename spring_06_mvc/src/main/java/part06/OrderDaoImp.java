package part06;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class OrderDaoImp implements OrderDAO {
	private SqlSessionTemplate sqlSession;

	public OrderDaoImp() {
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertMethod(OrderDTO dto) {
		sqlSession.insert("order.ins", dto);
	}

	@Override
	public List<OrderDTO> selectMethod() {
		return sqlSession.selectList("order.all");
	}

}
