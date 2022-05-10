package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.StockLine;

public class StockLineDB implements StockLineDBIF {
	private static final String FIND_STOCKLINEID = "select * from ProductStockLine where idProduct = ?";
	private static final String FIND_STOCKLINES = "select * from StockLine where idStockLine = ?";
	private static final String UPDATE_STOCKLINES = "Update StockLines set qtyAtLoc = ?, where idStockLine = ?";

	private PreparedStatement findStockLineIDPS;
	private PreparedStatement findStockLinesPS;
	private PreparedStatement updateStockLinesPS;

	public StockLineDB() {
		try {
			findStockLineIDPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_STOCKLINEID);
			findStockLinesPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_STOCKLINES);
			updateStockLinesPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_STOCKLINES);
		} catch (SQLException e) {
		}
	}

	@Override
	public List<StockLine> getStockLines(int idProduct) throws SQLException {
		List<StockLine> ListSL = new ArrayList();
		findStockLineIDPS.setInt(1, idProduct);
		ResultSet rs = findStockLineIDPS.executeQuery();
		int i = 0;
		while (rs.next()) {
			ListSL.add(createStockLine(rs.getInt("idStockLine")));
			i++;
		}
		return ListSL;
	}

	private StockLine createStockLine(int idStockLine) throws SQLException {
		findStockLinesPS.setInt(1, idStockLine);
		ResultSet rs = findStockLinesPS.executeQuery();
		StockLine sl = new StockLine(rs.getInt("qtyAtLoc"), rs.getInt(idStockLine));
		return sl;
	}

	public void updateStockLine(Product product) throws SQLException {
		List<StockLine> ls = product.getStockLines();
		for (int i = 0; i < ls.size(); i++) {
			updateStockLinesPS.setInt(1, ls.get(i).getQtyAtLoc());
			updateStockLinesPS.setInt(2, ls.get(i).getIdStockLine());
			updateStockLinesPS.executeQuery();
		}

	}
}
