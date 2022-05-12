package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

	private int orderNo;
	private int trackingNo;
	private int invoiceNo;
	private LocalDate orderDate;
	private LocalDate shipDate;
	private ArrayList orderLines;
	private String status;
	private Contact Customer;

	public Order() {
		orderLines = new ArrayList<>();
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(int trackingNo) {
		this.trackingNo = trackingNo;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShipDate() {
		return shipDate;
	}

	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}

	public ArrayList getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList orderLines) {
		this.orderLines = orderLines;
	}

	public Order addOrderLine(OrderLine ol) {
		orderLines.add(ol);
		return this;
	}

	public String toString() {
		return "Order [orderNo=" + orderNo + ", trackingNo=" + trackingNo + ", invoiceNo=" + invoiceNo + ", orderDate="
				+ orderDate + ", shippedDate=" + shipDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void addCustomer(Contact customer) {
		this.Customer = customer;
	}

}
