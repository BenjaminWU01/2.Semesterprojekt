package db;

import java.util.List;

import model.Order;

public interface OrderLineDBIF {

	public void commitOrderLines(Order order, List<Integer> idSQL);

}
