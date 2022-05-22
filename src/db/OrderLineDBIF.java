package db;

import java.sql.SQLException;
import java.util.List;

import model.Order;
import model.OrderLine;
import model.Size;

public interface OrderLineDBIF {

	public void commitOrderLine(OrderLine orderLine, String idOrder) throws SQLException;
}
