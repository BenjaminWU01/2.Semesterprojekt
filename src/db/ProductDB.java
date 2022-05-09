package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import model.Size;

public class ProductDB implements ProductDBIF {
	private static final String FIND_PRODUCT = "select * from Product where ?, ?";

	private PreparedStatement findProductPS;

	public ProductDB() {
		try {
			findProductPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRODUCT);
		} catch (SQLException e) {
		}
	}

	public Product getProduct(int productNo, String size) throws SQLException {
		Product p = null;
		findProductPS.setInt(1, productNo);
		findProductPS.setString(2, size);
		ResultSet rs = findProductPS.executeQuery();
		if (rs.next()) {
			p = buildProduct(rs);
		}
		return p;
	}

	private Product buildProduct(ResultSet rs) throws SQLException {
		Size s = new Size(rs.getString("sizeDesc"));
		Product p = new Product(rs.getString("prodNo"), rs.getString("prodDesc"), s);

		return p;

	}

}
