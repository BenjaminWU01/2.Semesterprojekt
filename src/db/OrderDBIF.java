package db;

import java.sql.ResultSet;
import java.util.List;

import model.Order;

public interface OrderDBIF {

	public List<Order> getOrders() throws DataAccessException;
	public Order buildOrder(ResultSet rs) throws DataAccessException;
	public List<Order> buildOrders(ResultSet rs) throws DataAccessException;
	public Order commitOrder(Order order) throws DataAccessException;
	
	
	// ---------------------------------- Future Use Cases/Iterations ---------------------------------- //
	
	
	public void updateOrderRunning(String orderNo) throws DataAccessException;
	public void updateOrderFinished(String orderNo) throws DataAccessException;
}
