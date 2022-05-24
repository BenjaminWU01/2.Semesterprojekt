package db;

import java.sql.SQLException;

import model.OrderLine;

public interface OrderLineDBIF {

	public void commitOrderLine(OrderLine orderLine, int idOrder) throws SQLException;
}
