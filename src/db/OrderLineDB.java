package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.OrderLine;
import model.Product;

public class OrderLineDB implements OrderLineDBIF {

	private static final String commitOrderLineQ = "INSERT INTO OrderLine VALUES (?, ?, ?, ?)";

	private PreparedStatement commitOrderLine;

	// Constructor
	public OrderLineDB() {
		try {
			commitOrderLine = DBConnection.getInstance().getConnection().prepareStatement(commitOrderLineQ);
		} catch (SQLException e) {
		}
	}
	
	// Commits an OrderLine to the DB
	public void commitOrderLine(OrderLine orderLine, int idOrder) throws DataAccessException {
		Product p = orderLine.getProduct();
		try {
			commitOrderLine.setInt(1, orderLine.getQuantity());
		
		commitOrderLine.setInt(2, idOrder);
		commitOrderLine.setInt(3, p.getIdProduct());
		commitOrderLine.setInt(4, p.getSize().getIdSize());
		System.out.println(orderLine.getQuantity() + "  " + idOrder + "  " + p.getIdProduct() + "  " + p.getSize().getIdSize());
		commitOrderLine.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Couldn't commit orderLine");
		}
	}
}
