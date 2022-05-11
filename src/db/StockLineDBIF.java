package db;

import java.sql.SQLException;
import java.util.List;

import model.StockLine;

public interface StockLineDBIF {

	List<StockLine> getStockLines(int idProduct) throws SQLException;


}
