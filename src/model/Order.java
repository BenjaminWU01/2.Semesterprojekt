package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Order {

	private String orderNo;
	private int trackingNo;
	private int invoiceNo;
	private LocalDate orderDate;
	private LocalDate shipDate;
	private ArrayList<OrderLine> orderLines;
	private String status;
	private Contact customer;

	public Order() {
		orderLines = new ArrayList<OrderLine>();
		Random random = new Random();
		orderNo = "" + random.nextInt(100000000);
		trackingNo = random.nextInt(100000000);
		invoiceNo = random.nextInt(100000000);
		orderDate = LocalDate.now();
	}

	public Order(String orderNo, LocalDate orderDate, int trackingNo, int invoiceNo, String status) {
		orderLines = new ArrayList<OrderLine>();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.trackingNo = trackingNo;
		this.invoiceNo = invoiceNo;
		this.status = status;
	}
	
	public Order addOrderLine(OrderLine ol) {
		orderLines.add(ol);
		return this;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
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

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void addCustomer(Contact customer) {
		this.customer = customer;
	}

	public Contact getContact() {
		return this.customer;
	}
	
	public String toString() {
		return "Order [orderNo=" + orderNo + ", trackingNo=" + trackingNo + ", invoiceNo=" + invoiceNo + ", orderDate="
				+ orderDate + ", shippedDate=" + shipDate + ", status=" + status;
	}
}
