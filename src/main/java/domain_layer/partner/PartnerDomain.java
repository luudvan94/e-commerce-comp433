package domain_layer.partner;

import java.util.List;

import entity.book.Book;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import exception.NotExistException;

public interface PartnerDomain {
	
	Partner getPartner(String id) throws NotExistException;
	
	PartnerInfo login(String username, String password) throws NotExistException;
	
	String register(String username, String password);
	
	void updatePassword(String id, String newPassword) throws NotExistException;
	
	void deleteCustomer(String id) throws NotExistException;
	
	List<Book> getBooksByPartnerID(String id);

}
