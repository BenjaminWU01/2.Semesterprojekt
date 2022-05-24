package db;

import model.OrderLine;

public interface OrderLineDBIF {

	public void commitOrderLine(OrderLine orderLine, int idOrder) throws DataAccessException;
}
