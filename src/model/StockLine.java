package model;

public class StockLine {
	private int qtyAtLoc;
	private int idStockLine;

	public StockLine(int qtyAtLoc, int idStockLine) {
		this.qtyAtLoc = qtyAtLoc;
		this.idStockLine = idStockLine;
	}
	
	public int getQtyAtLoc() {
		
		return this.qtyAtLoc;
	}
	
	public int getIdStockLine() {
		
		return this.idStockLine;
	}
	

}
