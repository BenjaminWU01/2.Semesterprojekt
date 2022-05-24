package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderLine;

public class OrderDB implements OrderDBIF {
	private static final String findAllOrdersQ = "select * from Orders LEFT JOIN OrderStatus on Orders.idOrderStatus = OrderStatus.idOrderStatus;";
	private static final String commitOrderQ = "INSERT INTO Orders VALUES(?, ?, ?, ?, ?, ?, ?) ";
	private static final String updateOrderRunningQ = "UPDATE Orders SET idOrderStatus = 2 WHERE orderNo = ?";
	private static final String updateOrderFinishedQ = "UPDATE Orders SET idOrderStatus = 3 WHERE orderNo = ?";

	private PreparedStatement findAllOrders;
	private PreparedStatement commitOrder;
	private PreparedStatement updateOrderRunning;
	private PreparedStatement updateOrderFinished;
	private OrderLineDBIF orderLineDB;

	// Constructs an OrderDB object, and initiates prepared statements
	public OrderDB() throws DataAccessException {
		orderLineDB = new OrderLineDB();
		try {
			findAllOrders = DBConnection.getInstance().getConnection().prepareStatement(findAllOrdersQ);
			commitOrder = DBConnection.getInstance().getConnection().prepareStatement(commitOrderQ,
					PreparedStatement.RETURN_GENERATED_KEYS);
			updateOrderRunning = DBConnection.getInstance().getConnection().prepareStatement(updateOrderRunningQ);
			updateOrderFinished = DBConnection.getInstance().getConnection().prepareStatement(updateOrderFinishedQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statements");
		}
	}

	// Gets all Orders from the DB
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

	// Builds a single Order object from the ResultSet from the DB
	public Order buildOrder(ResultSet rs) throws SQLException {
		Order o = new Order(rs.getString("orderNo"), rs.getDate("orderDate").toLocalDate(), rs.getInt("trackingNo"),
				rs.getInt("invoiceNo"), rs.getString("status"));
		if (rs.getDate("shipDate") != null) {
			o.setShipDate(rs.getDate("shipDate").toLocalDate());
		}
		return o;
	}

	// Builds every Order object from the ResultSet from the DB
	public List<Order> buildOrders(ResultSet rs) throws SQLException {
		List<Order> result = new ArrayList<>();
		while (rs.next()) {
			result.add(buildOrder(rs));
		}
		return result;
	}

	// Updates the oldest Orders status to running
	public void updateOrderRunning(String orderNo) throws DataAccessException {
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			updateOrderRunning.setString(1, orderNo);
			updateOrderRunning.execute();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e1) {
			throw new DataAccessException(e1, "Could not update order " + orderNo + "s status to running.");
		}
	}

	// Updates the selected Orders status to finished
	public void updateOrderFinished(String orderNo) throws DataAccessException {
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			updateOrderFinished.setString(1, orderNo);
			updateOrderFinished.execute();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e1) {
			throw new DataAccessException(e1, "Could not update order " + orderNo + "s status to finished.");
		}
	}

	// Commits an Order, and its OrderLines to the DB
	public Order commitOrder(Order order) throws DataAccessException {
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			commitOrder.setString(1, order.getOrderNo());
			commitOrder.setDate(2, Date.valueOf(LocalDate.now()));

			// Hardcoded shipDate to today:
			commitOrder.setDate(3, Date.valueOf(LocalDate.now()));
//			commitOrder.setDate(3, Date.valueOf(order.getShipDate()));

			commitOrder.setInt(4, order.getTrackingNo());
			commitOrder.setInt(5, order.getInvoiceNo());

			// Hardcoded customerNo to 1:
			commitOrder.setInt(6, 1);
//			commitOrder.setInt(6, order.getContact().getIdContact());

			commitOrder.setInt(7, 1);

			int idOrder = DBConnection.getInstance().executeInsertWithIdentity(commitOrder);
			DBConnection.getInstance().getConnection().commit();
			commitOrderLine(order, idOrder);
		} catch (SQLException e) {
			if (DBConnection.getInstance().getConnection() != null) {
				try {
					System.err.println("Something went wrong, the order will be rolled back");
					DBConnection.getInstance().getConnection().rollback();
				} catch (SQLException excep) {
					System.err.println("Sql rollback failed");
				}
			}
		}
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	// Commits an Orders OrderLines to the DB
	public void commitOrderLine(Order order, int idOrder) throws DataAccessException {
		for (int i = 0; i < order.getOrderLines().size(); i++) {
			OrderLine ol = order.getOrderLines().get(i);
			System.out.println(ol);
			try {
				orderLineDB.commitOrderLineIdentity(ol, idOrder);
			} catch (SQLException e) {
				throw new DataAccessException(e, "Error in OrderDB, in commitOrderLine");
			}
		}
	}
}
