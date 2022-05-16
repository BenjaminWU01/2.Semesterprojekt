package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Order;
import model.OrderLine;
import model.Size;

public class OrderLineDB implements OrderLineDBIF {

	private static final String COMMIT_ORDERLINES = "INSERT INTO OrderLine VALUES?, ?, ?, ?";

	private PreparedStatement commitOrderLinesPS;

	public OrderLineDB() {
		try {
			commitOrderLinesPS = DBConnection.getInstance().getConnection().prepareStatement(COMMIT_ORDERLINES);
		} catch (SQLException e) {
		}

	}

	// i 0 = idOrder, i 1 = idProduct, i 2 = idSize
	public void commitOrderLine(OrderLine orderLine, int idOrder, int idProduct, int idSize) throws SQLException {
		commitOrderLinesPS.setInt(1, orderLine.getQuantity());
		commitOrderLinesPS.setInt(2, idOrder);
		commitOrderLinesPS.setInt(3, idProduct);
		commitOrderLinesPS.setInt(4, idSize);
		commitOrderLinesPS.execute();

	}

}
