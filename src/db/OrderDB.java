package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderLine;
import model.Product;
import model.StockLine;

public class OrderDB implements OrderDBIF {
	private static final String findAllOrdersQ = "select * from Orders LEFT JOIN OrderStatus on Orders.idOrderStatus = OrderStatus.idOrderStatus;";
	private static final String COMMIT_ORDER = "INSERT INTO Orders VALUES(?, ?, ?, ?, ? ?, ? "
			+ "SELECT Orders.[idOrder], Product.[idProduct], Product.[idSize] FROM OrderS "
			+ "INNER JOIN OrderLine ON Orders.idOrder = OrderLine.idOrder "
			+ "INNER JOIN Product ON OrderLine.idProduct = Product.idProduct " + "WHERE Orders.idOrder = 1";
	private static final String updateOrderToRunning = "UPDATE Orders SET idOrderStatus = 2 WHERE orderNo = ?";
	private static final String updateOrderToFinished = "UPDATE Orders SET idOrderStatus = 3 WHERE orderNo = ?";

	private PreparedStatement findAllOrders;
	private PreparedStatement commitOrderPS;
	private PreparedStatement toRunning;
	private PreparedStatement toFinished;
	private OrderLineDBIF orderLineDB;

	// Test, delete later
	public static void main(String[] args) throws DataAccessException {
		OrderDB odb = new OrderDB();
		System.out.println(odb.getOrders());
	}

	public OrderDB() throws DataAccessException {
		orderLineDB = new OrderLineDB();
		try {
			findAllOrders = DBConnection.getInstance().getConnection().prepareStatement(findAllOrdersQ);
			commitOrderPS = DBConnection.getInstance().getConnection().prepareStatement(COMMIT_ORDER,
					PreparedStatement.RETURN_GENERATED_KEYS);
			toRunning = DBConnection.getInstance().getConnection().prepareStatement(updateOrderToRunning);
			toFinished = DBConnection.getInstance().getConnection().prepareStatement(updateOrderToFinished);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statements");
		}
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
		Order o = new Order(rs.getString("orderNo"), rs.getDate("orderDate").toLocalDate(), rs.getInt("trackingNo"),
				rs.getInt("invoiceNo"), rs.getInt("idContact"), rs.getString("status"));
		if (rs.getDate("shipDate") != null) {
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

	public void processOldestOrder(String orderNo) throws SQLException, DataAccessException {
		DBConnection.getInstance().getConnection().setAutoCommit(false);
		toRunning.setString(1, orderNo);
		toRunning.execute();
		DBConnection.getInstance().getConnection().setAutoCommit(true);
	}
	
	public void finishOrder(String orderNo) throws SQLException, DataAccessException{
		DBConnection.getInstance().getConnection().setAutoCommit(false);
		toFinished.setString(1, orderNo);
		toFinished.execute();
		DBConnection.getInstance().getConnection().setAutoCommit(true);
	}

	public Order commitOrder(Order order) throws SQLException, DataAccessException {
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			commitOrderPS.setString(1, order.getOrderNo());
			commitOrderPS.setDate(2, Date.valueOf(order.getOrderDate()));
			commitOrderPS.setDate(3, Date.valueOf(order.getShipDate()));
			commitOrderPS.setInt(4, order.getTrackingNo());
			commitOrderPS.setInt(5, order.getInvoiceNo());
			commitOrderPS.setInt(6, order.getContact().getIdContact());
			commitOrderPS.setInt(7, 1);
			int idOrder = DBConnection.getInstance().executeInsertWithIdentity(commitOrderPS);
			for (int i = 0; i < order.getOrderLines().size(); i++) {
				OrderLine ol = (OrderLine) order.getOrderLines().get(i);
				orderLineDB.commitOrderLine(ol, idOrder);
			}
			DBConnection.getInstance().getConnection().commit();
		} catch (SQLException e) {
			if (DBConnection.getInstance().getConnection() != null) {

				try {
					System.err.println("something went wrong, the order will be rolled back");
					DBConnection.getInstance().getConnection().rollback();
				} catch (SQLException excep) {
					System.err.println("Sql rollback failed");
				}
			}

		}
		DBConnection.getInstance().getConnection().setAutoCommit(true);
		return order;
	}
}
