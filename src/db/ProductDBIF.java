package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.Size;
import model.StockLine;

public interface ProductDBIF {

	public Product getProduct(String productNo, Size size) throws SQLException;

	public List<StockLine> getStockLine(int idProduct) throws SQLException;

	public void updateStockLine(Product product);

	public List<Product> buildAllProduct();

}
