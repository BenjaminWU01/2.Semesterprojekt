package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.Size;
import model.StockLine;

public class ProductDB implements ProductDBIF {
	private static final String FIND_PRODUCT = "select * from Product "
			+ "FULL OUTER JOIN Size ON Size.idSize = Product.idSize WHERE prodNo = ? and sizeDesc = ? ";

	private PreparedStatement findProductPS;
	private StockLineDBIF stockLineDB;

	public ProductDB() {
		stockLineDB = new StockLineDB();
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
			int idProduct = rs.getInt("idProduct");
			getStockLine(idProduct);
		}
		return p;
	}

	private Product buildProduct(ResultSet rs) throws SQLException {
		Size s = new Size(rs.getString("sizeDesc"));
		Product p = new Product(rs.getString("prodNo"), rs.getString("prodDesc"), s);

		return p;

	}

	public List<StockLine> getStockLine(int idProduct) throws SQLException {
		List<StockLine> stockLines = stockLineDB.getStockLines(idProduct);
		return null;

	}

	public void updateStockLine() {

	}

}
