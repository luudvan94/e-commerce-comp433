package util;

import java.util.Date;

import entity.Book;
import entity.BookReview;
import entity.Partner;
import entity.PartnerInfo;

public class EntityUtil {
	
	public static Book bookSample() {
		Book book = new Book();
		book.setId(Book.generateID());
		book.setTitle("Data Smart: Using Data Science to Transform Information into Insight");
		book.setAuthor("John W. Foreman");
		book.setDescription("Data Science gets thrown around in the press like it's magic. Major retailers are predicting everything from when their customers are pregnant to when they want a new pair of Chuck Taylors. It's a brave new world where seemingly meaningless data can be transformed into valuable insight to drive smart business decisions.");
		book.setPrice(22.95);
		book.setPartnerID("P1234");
		
		return book;
	}
	
	public static Partner partnerSample() {
		Partner partner = new Partner();
		partner.setId(Partner.generateID());
		partner.setUsername("username");
		partner.setPassword(Partner.encrypt("password"));
		
		return partner;
	}
	
	public static PartnerInfo partnerInfoSample() {
		PartnerInfo info = new PartnerInfo();
		info.setId(PartnerInfo.generateID());
		info.setName("partner info");
		info.setAddress("123 Sherindan Av");
		info.setDate_added("" + new Date().getTime());
		info.setPartnerID("P1234");
		
		return info;
	}
	
	public static BookReview bookReviewSample() {
		BookReview review = new BookReview();
		review.setId(BookReview.generateID());
		review.setContent("this is awesome!!");
		review.setDateCreated("" + new Date().getTime());
		review.setBook_id("B123");
		
		return review;
	}

}
