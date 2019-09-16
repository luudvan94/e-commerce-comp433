package entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;  

import javax.persistence.*;

@Entity
@Table(name="BOOK_REVIEW")
public class BookReview {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="bookID")
	private String bookID;
	
	@Column(name="date_created")
	private String dateCreated;
	
	public BookReview() {}
	
	public static String generateID() {
		return "BR" + new Date().getTime();
	}

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

	public String getBook_id() {
		return bookID;
	}

	public void setBook_id(String bookID) {
		this.bookID = bookID;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
