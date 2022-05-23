package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Order;
import model.OrderLine;
import model.Product;
import model.Size;

public class OrderLineDB implements OrderLineDBIF {

	private static final String COMMIT_ORDERLINE = "INSERT INTO OrderLine VALUES (?, ?, ?, ?)";

	private PreparedStatement commitOrderLinePS;

	public OrderLineDB() {
		try {
			commitOrderLinePS = DBConnection.getInstance().getConnection().prepareStatement(COMMIT_ORDERLINE);
		} catch (SQLException e) {
		}

	}

	// i 0 = idOrder, i 1 = idProduct, i 2 = idSize
	public void commitOrderLine(OrderLine orderLine, String idOrder) throws SQLException {

		Product p = orderLine.getProduct();

		commitOrderLinePS.setInt(1, orderLine.getQuantity());
		commitOrderLinePS.setString(2, idOrder);
		commitOrderLinePS.setInt(3, p.getIdProduct());
		commitOrderLinePS.setInt(4, p.getSize().getIdSize());
		commitOrderLinePS.execute();

	}
	
	//TEMPORARY TEST, IDENTITY TEST, DELETE LATER
	public void commitOrderLineIdentity(OrderLine orderLine, int idOrder) throws SQLException {

		Product p = orderLine.getProduct();

		commitOrderLinePS.setInt(1, orderLine.getQuantity());
		commitOrderLinePS.setInt(2, idOrder);
		commitOrderLinePS.setInt(3, p.getIdProduct());
		commitOrderLinePS.setInt(4, p.getSize().getIdSize());
		System.out.println(orderLine.getQuantity() + "  " + idOrder + "  " + p.getIdProduct() + "  " + p.getSize().getIdSize());
		commitOrderLinePS.executeUpdate();

	}

}
