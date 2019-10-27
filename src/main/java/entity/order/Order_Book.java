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
import entity.partner.PartnerInfo;

@Entity
@Table(name = "ORDER_BOOK")
public class Order_Book {
	
	@Id
	@Column(name = "orderBookID", unique = true, nullable = false)
	private String orderBookID;
	
	@ManyToOne
    @JoinColumn(name="bookID", nullable=true)
    private Book book;
	
	@ManyToOne
    @JoinColumn(name="partnerInfoID", nullable=true)
    private PartnerInfo partnerInfo;
	
	@Column(name="qty")
	private int qty;
	
	public PartnerInfo getPartnerInfo() {
		return partnerInfo;
	}

	public void setPartnerInfo(PartnerInfo partnerInfo) {
		this.partnerInfo = partnerInfo;
	}

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


	public String getOrderBookID() {
		return orderBookID;
	}

	public void setOrderBookID(String orderBookID) {
		this.orderBookID = orderBookID;
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
