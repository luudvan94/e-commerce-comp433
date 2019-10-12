package domain_layer.partner;

import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.partner.PartnerInfoRepository;
import dal.partner.PartnerInfoRepositoryImpl;
import dal.partner.PartnerRepository;
import dal.partner.PartnerRepositoryImpl;
import entity.book.Book;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import exception.NotExistException;
import util.ID;

public class PartnerDomainImpl implements PartnerDomain {
	
	private PartnerRepository partnerRepository = new PartnerRepositoryImpl();
	private PartnerInfoRepository partnerInfoRepository = new PartnerInfoRepositoryImpl();
	private BookRepository bookRepository = new BookRepositoryImpl();

	@Override
	public Partner getPartner(String id) throws NotExistException {
		Partner partner = partnerRepository.get(id);
		
		if (partner == null) {
			throw new NotExistException("Partner with provided id does not exist");
		}
		
		return partner;
	}

	@Override
	public PartnerInfo login(String username, String password) throws NotExistException {
		Partner partner = partnerRepository.partnerWithUsernamePassword(username, password);
		
		if (partner == null) {
			throw new NotExistException("Partner with provided user name and password does not exist");
		}
		
		PartnerInfo partnerInfo = partnerInfoRepository.partnerInfobyPartnerID(partner.getId());
		
		if (partnerInfo == null) {
			throw new NotExistException("Partner info with provided id does not exist");
		}
		
		return partnerInfo;
	}

	@Override
	public String register(String username, String password) {
		Partner partner = new Partner();
		partner.setId(ID.generateID("P"));
		partner.setUsername(username);
		partner.setPassword(password);
		
		return partnerRepository.create(partner);
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
	public List<Book> getBooksByPartnerID(String id) {
		return bookRepository.booksByPartnerID(id);
	}

	

}
