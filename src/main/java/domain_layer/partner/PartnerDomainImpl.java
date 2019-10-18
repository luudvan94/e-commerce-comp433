package domain_layer.partner;

import java.util.Date;
import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.partner.PartnerInfoRepository;
import dal.partner.PartnerInfoRepositoryImpl;
import dal.partner.PartnerRepository;
import dal.partner.PartnerRepositoryImpl;
import domain_layer.book.BookDomain;
import domain_layer.book.BookDomainImpl;
import entity.book.Book;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import util.ID;
import util.Password;

public class PartnerDomainImpl implements PartnerDomain {
	
	private PartnerRepository partnerRepository = new PartnerRepositoryImpl();
	private PartnerInfoRepository partnerInfoRepository = new PartnerInfoRepositoryImpl();
	private BookRepository bookRepository = new BookRepositoryImpl();
	
	private BookDomain bookDomain = new BookDomainImpl();

	@Override
	public Partner getPartner(String id) throws NotExistException {
		Partner partner = partnerRepository.get(id);
		
		if (partner == null) {
			throw new NotExistException("Partner with provided id does not exist");
		}
		
		return partner;
	}

	@Override
	public Partner login(String username, String password) throws NotExistException {
		Partner partner = partnerRepository.partnerWithUsernamePassword(username, password);
		
		if (partner == null) {
			throw new NotExistException("Partner with provided user name and password does not exist");
		}
		
		return partner;
	}

	@Override
	public String register(String username, String password) throws AlreadyExistedException {
		Partner partner = new Partner();
		partner.setPartnerID(ID.generateID("P"));
		partner.setUsername(username);
		partner.setPassword(Password.encrypt(password));
		
		String partnerID = partnerRepository.create(partner);
		
		if (partnerID == null) {
			throw new AlreadyExistedException("Partner with provided username already existed");
		}
		
		return partnerID;
	}

	@Override
	public void updatePassword(String id, String newPassword) throws NotExistException {
		Partner partner = partnerRepository.get(id);
		
		partner.setPassword(newPassword);
		
		partnerRepository.update(partner);
		
	}

	@Override
	public void deleteCustomer(String id) throws NotExistException {
		 Partner partner = partnerRepository.get(id);
		 
		 partnerRepository.delete(partner);
		
	}

	@Override
	public String addPartnerInfo(String partnerID, String name, String address) throws NotExistException, AlreadyExistedException {
		Partner partner = this.getPartner(partnerID);
		
		PartnerInfo partnerInfo = partnerInfoRepository.partnerInfobyPartnerID(partnerID);
		
		if (partnerInfo != null) {
			throw new AlreadyExistedException("Partner with provided id already had partner information");
		}
		
		PartnerInfo newInfo = new PartnerInfo();
		newInfo.setPartnerInfoID(ID.generateID("PI"));
		newInfo.setPartnerID(partner.getPartnerID());
		newInfo.setName(name);
		newInfo.setAddress(address);
		newInfo.setDate_added(Long.toString(new Date().getTime()));
		
		String newID = partnerInfoRepository.create(newInfo);
		
		return newID;
	}

	@Override
	public PartnerInfo getPartnerInfo(String partnerID) throws NotExistException {
		PartnerInfo partnerInfo = partnerInfoRepository.partnerInfobyPartnerID(partnerID);
		
		if (partnerInfo == null) {
			throw new NotExistException("Partner info with provided id does not exist");
		}
		
		return partnerInfo;
	}

	@Override
	public List<Book> getBooksByPartnerID(String id) {
		return bookDomain.getBooksByParterInfoID(id);
	}

	@Override
	public void deleteBook(String partnerID, String bookID) throws NotExistException, UnAuthorizedException {
		Book book = bookDomain.getBookById(bookID);
		
		if (!book.getPartnerInfo().getPartnerID().equalsIgnoreCase(partnerID)) {
			throw new UnAuthorizedException("This book can not be deleted by partner with provided ID");
		}
		
		bookDomain.deleteBook(bookID);
		
	}

	@Override
	public PartnerInfo updatePartnerInfo(String partnerID, String name, String address) throws NotExistException {
		PartnerInfo info = this.getPartnerInfo(partnerID);
		
		info.setName(name);
		info.setAddress(address);
		
		partnerInfoRepository.update(info);
		
		return info;
	}

	

}
