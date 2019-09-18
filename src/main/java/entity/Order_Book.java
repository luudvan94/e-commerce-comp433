package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_BOOK")
public class Order_Book {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name="orderID")
	private String orderID;
	
	@Column(name="bookID")
	private String bookID;
	
	@Column(name="qty")
	private int qty;
	
	@Column(name="total")
	private double total;
	
	
	public Order_Book() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	

}
