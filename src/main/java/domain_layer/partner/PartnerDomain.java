package domain_layer.partner;

import java.util.List;

import entity.book.Book;
import entity.customer.CustomerInfo;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;

public interface PartnerDomain {
	
	Partner getPartner(String id) throws NotExistException;
	
	Partner login(String username, String password) throws NotExistException;
	
	String register(String username, String password) throws AlreadyExistedException;
	
	void updatePassword(String id, String newPassword) throws NotExistException;
	
	void deleteCustomer(String id) throws NotExistException;
	
	String addPartnerInfo(String partnerID, String name, String address) throws NotExistException, AlreadyExistedException;
	
	PartnerInfo getPartnerInfo(String partnerID) throws NotExistException;
	
	List<Book> getBooksByPartnerID(String id);
	
	void deleteBook(String partnerID, String bookID) throws NotExistException, UnAuthorizedException;
	
	PartnerInfo updatePartnerInfo(String partnerID, String name, String address) throws NotExistException;
}
