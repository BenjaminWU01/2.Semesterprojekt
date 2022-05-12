package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Order;
import model.OrderLine;

public class OrderlineDB implements OrderlineDBIF {

	private static final String COMMIT_ORDERLINES = "INSERT INTO OrderLine VALUES?, ?, ?, ?";

	private PreparedStatement commitOrderLinesPS;

	public OrderlineDB() {
		try {
			commitOrderLinesPS = DBConnection.getInstance().getConnection().prepareStatement(COMMIT_ORDERLINES);
		} catch (SQLException e) {
		}

	}

	public void commitOrderLines(Order order, List<Integer> idSQL) {
		List<OrderLine> tempList = order.getOrderLines();
		try {
			for (int i = 0; i < tempList.size(); i++) {
				OrderLine ol = tempList.get(i);
				commitOrderLinesPS.setInt(1, ol.getQuantity());
				commitOrderLinesPS.setInt(2, idSQL.get(0));
				commitOrderLinesPS.setInt(3, idSQL.get(1));
				commitOrderLinesPS.setInt(4, idSQL.get(2));
				commitOrderLinesPS.execute();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
