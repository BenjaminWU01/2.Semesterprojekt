package model;

public class StockLine {
	private int qtyAtLoc;
	private int idStockLine;
	
	//Adding New Stock Line
	public StockLine(int qtyAtLoc) {
		this.qtyAtLoc = qtyAtLoc;
	}

	//Retreived Stock Line From SQL Database
	public StockLine(int qtyAtLoc, int idStockLine) {
		this.qtyAtLoc = qtyAtLoc;
		this.idStockLine = idStockLine;
	}
	
	public int getQtyAtLoc() {
		return this.qtyAtLoc;
	}
	
	public void setQtyAtLoc(int newQty) {
		this.qtyAtLoc = newQty;
	}
	
	public int getIdStockLine() {
		return this.idStockLine;
	}
	

}
