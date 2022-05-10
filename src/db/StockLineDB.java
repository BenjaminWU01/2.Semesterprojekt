package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.StockLine;

public class StockLineDB implements StockLineDBIF {
	private static final String FIND_STOCKLINEID = "select * from ProductStockLine where idProduct = ?";
	private static final String FIND_STOCKLINES = "select * from StockLine where idStockLine = ?";

	private PreparedStatement findStockLineIDPS;
	private PreparedStatement findStockLinesPS;

	public StockLineDB() {
		try {
			findStockLineIDPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_STOCKLINEID);
			findStockLinesPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_STOCKLINES);
		} catch (SQLException e) {
		}
	}

	@Override
	public List<StockLine> getStockLines(int idProduct) throws SQLException {
		findStockLineIDPS.setInt(1, idProduct);
		ResultSet rs = findStockLineIDPS.executeQuery();
		int i = 0;
		while (rs.next()) {
			createStockLine(rs.getInt("idStockLine"));
			i++;
		}
		return null;
	}

	private List<StockLine> createStockLine(int idStockLine) throws SQLException {
		findStockLinesPS.setInt(1, idStockLine);
		ResultSet rs = findStockLinesPS.executeQuery();
		return null;
	}

}
