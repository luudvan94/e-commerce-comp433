package domain.book_review;

import java.util.Date;  

import javax.persistence.*;

import domain.customer.CustomerInfo;

@Entity
@Table(name="BOOK_REVIEW")
public class BookReview {

	@Id
	@Column(name = "bookReviewID", unique = true, nullable = false)
	private String id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="bookID")
	private String bookID;
	
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

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
}
