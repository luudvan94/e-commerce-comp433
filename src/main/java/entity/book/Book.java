package entity.book;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import entity.book_review.BookReview;
import entity.order.Order_Book;
import entity.partner.Partner;
import entity.partner.PartnerInfo;

@Entity
@Table(name = "BOOK")
public class Book {
	
	@Id
	@Column(name = "bookID", unique = true, nullable = false)
	private String bookID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="partnerInfoID", nullable=true)
	private PartnerInfo partnerInfo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="book")
	private List<BookReview> reviews;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="book")
    private List<Order_Book> orderBooks;
	

	public PartnerInfo getPartnerInfo() {
		return partnerInfo;
	}

	public void setPartnerInfo(PartnerInfo partnerInfo) {
		this.partnerInfo = partnerInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
//	@Transient
//	private List<BookReview> reviews;
	
	
	public List<BookReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<BookReview> reviews) {
		this.reviews = reviews;
	}

	public Book() {}

	

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//
//	public List<BookReview> getReviews() {
//		return reviews;
//	}
//
//	public void setReviews(List<BookReview> reviews) {
//		this.reviews = reviews;
//	}
	
	
}
