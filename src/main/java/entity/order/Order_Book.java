package entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import entity.book.Book;
import entity.customer.CustomerInfo;

@Entity
@Table(name = "ORDER_BOOK")
public class Order_Book {
	
	@Id
	@Column(name = "orderBookID", unique = true, nullable = false)
	private String id;
	
	@ManyToOne
    @JoinColumn(name="bookID", nullable=true)
    private Book book;
	
	@Column(name="qty")
	private int qty;
	
	@Column(name="total")
	private double total;
	
//	@Transient
//	private Book book;
	
	@ManyToOne
    @JoinColumn(name="orderID", nullable=true)
    private Order1 order;
	
	
	public Order_Book() {}

	public Order1 getOrder() {
		return order;
	}

	public void setOrder(Order1 order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
