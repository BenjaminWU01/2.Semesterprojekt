package db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderLine;

public interface OrderDBIF {

	public List<Order> getOrders() throws DataAccessException;

	public Order buildOrder(ResultSet rs) throws SQLException;

	public List<Order> buildOrders(ResultSet rs) throws SQLException;

	public Order commitOrder(Order order) throws SQLException, DataAccessException;

}
