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
	private int contactId;

	public Order() {
		Random random = new Random();
		orderLines = new ArrayList<>();
		orderNo = "" + random.nextInt();
		trackingNo = random.nextInt();
		invoiceNo = random.nextInt();
		orderDate = LocalDate.now();
	}

	public Order(String orderNo, LocalDate orderDate, int trackingNo, int invoiceNo, int contact, String status) {
		orderLines = new ArrayList<>();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.trackingNo = trackingNo;
		this.invoiceNo = invoiceNo;
		this.contactId = contact;
		this.status = status;
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

	public ArrayList getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Order addOrderLine(OrderLine ol) {
		orderLines.add(ol);
		return this;
	}

	public String toString() {
		return "Order [orderNo=" + orderNo + ", trackingNo=" + trackingNo + ", invoiceNo=" + invoiceNo + ", orderDate="
				+ orderDate + ", shippedDate=" + shipDate + ", status=" + status;
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

}
