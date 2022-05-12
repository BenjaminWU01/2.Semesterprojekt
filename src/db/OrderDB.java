package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Product;
import model.StockLine;

public class OrderDB implements OrderDBIF {
	private static final String findAllOrdersQ = "select * from Orders LEFT JOIN OrderStatus on Orders.idOrderStatus = OrderStatus.idOrderStatus;";

	private PreparedStatement findAllOrders;

	//Test, delete later
//	public static void main(String[] args) throws DataAccessException {
//		OrderDB odb = new OrderDB();
//		System.out.println(odb.getOrders());
//	}
	
	public OrderDB() throws DataAccessException {
		try {
			findAllOrders = DBConnection.getInstance().getConnection().prepareStatement(findAllOrdersQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statements");
		}
	}

	public List<StockLine> getStockLine(int productNo, String size) throws SQLException {
//			findProductPS.setInt(1, productNo);
//			findProductPS.setString(2, size);
//			ResultSet rs = findProductPS.executeQuery();

		return null;
	}

	public List<Order> getOrders() throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAllOrders.executeQuery();
			List<Order> result = buildOrders(rs);
			return result;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve all orders");
		}

	}

	public Order buildOrder(ResultSet rs) throws SQLException {
		Order o = new Order(rs.getString("orderNo"), 
				rs.getDate("orderDate").toLocalDate(), rs.getInt("trackingNo"), 
				rs.getInt("invoiceNo"), rs.getInt("idContact"), 
				rs.getString("status"));
		if(rs.getDate("shipDate") != null) {
			o.setShipDate(rs.getDate("shipDate").toLocalDate());
		}
		return o;
	}

	public List<Order> buildOrders(ResultSet rs) throws SQLException {
		List<Order> result = new ArrayList<>();
		while (rs.next()) {
			result.add(buildOrder(rs));
		}
		return result;
	}

	public static void processOldestOrder(String orderNo) {
		// Prepared Statement til Benny tak

	}

}
