package entity.partner;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import entity.book.Book;
import entity.book_review.BookReview;
import entity.order.Order_Book;


@Entity
@Table(name = "PARTNER_INFO")
public class PartnerInfo {
	@Id
	@Column(name = "partnerInfoID", unique = true, nullable = false)
	private String partnerInfoID;
	
	@Column(name = "partnerID")
	private String partnerID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="date_added")
	private String date_added;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="partnerInfo")
	private List<Book> books;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="partnerInfo")
	private List<Order_Book> orderBooks;
	
	public List<Order_Book> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<Order_Book> orderBooks) {
		this.orderBooks = orderBooks;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public PartnerInfo() {}
	
	public static String generateID() {
		return "PI" + new Date().getTime();
	}


	public String getPartnerInfoID() {
		return partnerInfoID;
	}

	public void setPartnerInfoID(String partnerInfoID) {
		this.partnerInfoID = partnerInfoID;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

}
