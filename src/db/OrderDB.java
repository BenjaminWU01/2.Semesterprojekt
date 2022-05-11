package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.StockLine;

public class OrderDB implements OrderDBIF {
	private static final String FIND_PRODUCT = "select * from Product where ?, ?";

	private PreparedStatement findProductPS;

	public OrderDB() {
		try {
			findProductPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRODUCT);
		} catch (SQLException e) {
		}
	}
	
	public List<StockLine> getStockLine(int productNo, String size) throws SQLException {
			findProductPS.setInt(1, productNo);
			findProductPS.setString(2, size);
			ResultSet rs = findProductPS.executeQuery();

		return null;
	}

	public static ResultSet getAllOrders() {
		return null;
		
		
	}

	public static void processOldestOrder(String orderNo) {
		// Prepared Statement til Benny tak
		
	}
	
	

}
