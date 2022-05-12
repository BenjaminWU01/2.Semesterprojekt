package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.Size;
import model.StockLine;

public class ProductDB implements ProductDBIF {
	private static final String FIND_PRODUCT = "SELECT DISTINCT Product.[idProduct], [sizeDesc], [prodNo], [prodDesc] from Product "
			+ "LEFT JOIN Size " + "ON Size.idSize = Product.idSize " + "INNER JOIN ProductStockLine "
			+ "ON ProductStockLine.idProduct = Product.idProduct" + " INNER JOIN StockLine "
			+ "ON ProductStockLine.idStockLine = StockLine.idStockLine" + " WHERE prodNo = ? and sizeDesc = ?  ";

	private PreparedStatement findProductPS;
	private StockLineDBIF stockLineDB;

	public ProductDB() {
		stockLineDB = new StockLineDB();
		try {
			findProductPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRODUCT);
		} catch (SQLException e) {
		}
	}

	public Product getProduct(String productNo, Size size) {
		Product p = null;
		try {
			findProductPS.setString(1, productNo);
			findProductPS.setString(2, size.getSizeDesc());
			ResultSet rs = findProductPS.executeQuery();
			if (rs.next()) {
				p = buildProduct(rs);
				int idProduct = rs.getInt("idProduct");
				getStockLine(idProduct);
			}
		} catch (SQLException e) {
			System.out.println("Produktet kunne ikke findes");
			e.printStackTrace();
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
		return stockLines;

	}

	public void updateStockLine(Product product) {

	}

}
