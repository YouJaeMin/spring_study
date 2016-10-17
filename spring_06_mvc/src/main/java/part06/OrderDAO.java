package part06;

import java.util.List;

public interface OrderDAO {
	public void insertMethod(OrderDTO dto);

	public List<OrderDTO> selectMethod();
}
