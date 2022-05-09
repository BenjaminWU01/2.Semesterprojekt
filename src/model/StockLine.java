package model;

public class StockLine {
	private int qtyAtLoc;

	public StockLine(int qtyAtLoc) {
		this.qtyAtLoc = qtyAtLoc;
	}

	public void updateStockLine(int qtyAtLoc) {
		this.qtyAtLoc = qtyAtLoc;
	}

}
