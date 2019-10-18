package entity.book_review;

import java.util.Date;  

import javax.persistence.*;

import entity.book.Book;
import entity.customer.CustomerInfo;

@Entity
@Table(name="BOOK_REVIEW")
public class BookReview {

	@Id
	@Column(name = "bookReviewID", unique = true, nullable = false)
	private String id;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne
    @JoinColumn(name="bookID", nullable=true)
	private Book book;
	
	@Column(name="date_created")
	private String dateCreated;
	
	@ManyToOne
    @JoinColumn(name="customerInfoID", nullable=true)
    private CustomerInfo customerInfo;
	
	public BookReview() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
//
//	public String getCustomerID() {
//		return customerID;
//	}
//
//	public void setCustomerID(String customerID) {
//		this.customerID = customerID;
//	}


	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
}
