package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.Size;

public class ProductDB implements ProductDBIF {
	private static final String FIND_PRODUCT = "SELECT DISTINCT Product.[idProduct], [sizeDesc], Size.[idSize], [prodNo], [prodDesc] from Product "
			+ "LEFT JOIN Size " + "ON Size.idSize = Product.idSize " + "INNER JOIN ProductStockLine "
			+ "ON ProductStockLine.idProduct = Product.idProduct" + " INNER JOIN StockLine "
			+ "ON ProductStockLine.idStockLine = StockLine.idStockLine" + " WHERE prodNo = ? and sizeDesc = ?  ";
	private static final String GET_ALL_PRODUCT = "SELECT DISTINCT Product.[idProduct], [sizeDesc], Size.[idSize], [prodNo], [prodDesc] from Product INNER JOIN Size ON Product.idSize = Size.idSize "
			+ "INNER JOIN ProductStockLine " + "ON ProductStockLine.idProduct = Product.idProduct"
			+ " INNER JOIN StockLine " + "ON ProductStockLine.idStockLine = StockLine.idStockLine";

	private PreparedStatement findProductPS;
	private PreparedStatement getAllProductPS;

	public ProductDB() throws DataAccessException {
		try {
			findProductPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRODUCT);
			getAllProductPS = DBConnection.getInstance().getConnection().prepareStatement(GET_ALL_PRODUCT);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Couldn't prepare statements");
		}
	}

	public Product getProduct(String productNo, Size size) throws DataAccessException {
		Product p = null;
		try {
			findProductPS.setString(1, productNo);
			findProductPS.setString(2, size.getSizeDesc());
			ResultSet rs = findProductPS.executeQuery();
			if (rs.next()) {
				p = buildProduct(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e,
					"Couldn't get product with productNo = " + productNo + " and size = " + size);
		}
		return p;
	}

	private Product buildProduct(ResultSet rs) throws DataAccessException {
		Size s;
		Product p = null;
		try {
			s = new Size(rs.getString("sizeDesc"), rs.getInt("idSize"));
			p = new Product(rs.getString("prodNo"), rs.getString("prodDesc"), s, rs.getInt("idProduct"));
		} catch (SQLException e) {
			throw new DataAccessException(e, "Couldn't build products");
		}
		return p;
	}

	public List<Product> buildAllProduct() throws DataAccessException {
		ArrayList<Product> product = new ArrayList<Product>();
		try {
			ResultSet rs = getAllProductPS.executeQuery();
			while (rs.next()) {
				product.add(buildProduct(rs));
			}
			for (int i = 0; i < product.size(); i++) {
				System.out.println(product.get(i).toString());
			}
		} catch (SQLException e) {
			throw new DataAccessException(e, "");
		}
		return product;
	}
}