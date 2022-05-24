package model;

import java.time.LocalDate;

public class Price {

	private int price;
	private LocalDate validFromDate;
	
	public Price(int price, LocalDate validFromDate) {
		this.price = price;
		this.validFromDate = validFromDate;
		
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getValidFromDate() {
		return validFromDate;
	}

	public void setValidFromDate(LocalDate validFromDate) {
		this.validFromDate = validFromDate;
	}
}
