package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class OrderDB implements OrderDBIF {
	private static final String FIND_PRODUCT = "select * from Product where ?, ?";

	private PreparedStatement findProductPS;

	public OrderDB() {
		try {
			findProductPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRODUCT);
		} catch (SQLException e) {
		}
	}
	
	public ResultSet getProduct(int productNo, String size) {
		try {
			findProductPS.setInt(1, productNo);
			findProductPS.setString(2, size);
			ResultSet rs = findProductPS.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	

}
