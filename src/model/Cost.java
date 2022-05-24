package model;

import java.time.LocalDate;

public class Cost {

	private int cost;
	private LocalDate dateRegister;
	private int qtyBought;

	public Cost(int cost, LocalDate dateRegister, int qtyBought) {
		this.cost = cost;
		this.dateRegister = dateRegister;
		this.qtyBought = qtyBought;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public LocalDate getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(LocalDate dateRegister) {
		this.dateRegister = dateRegister;
	}

	public int getQtyBought() {
		return qtyBought;
	}

	public void setQtyBought(int qtyBought) {
		this.qtyBought = qtyBought;
	}
}
