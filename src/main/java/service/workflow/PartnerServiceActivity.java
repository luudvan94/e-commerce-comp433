package service.workflow;

import java.util.List;
import java.util.stream.Collectors;

import domain_layer.partner.PartnerDomain;
import domain_layer.partner.PartnerDomainImpl;
import entity.book.Book;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookRepresentation;
import representation.PartnerInfoRepresentation;
import representation.PartnerRepresentation;
import service.util.RepresentationConverter;

public class PartnerServiceActivity {
	
	private static PartnerDomain partnerDomain = new PartnerDomainImpl();
	
	public PartnerRepresentation register(String username, String password) throws AlreadyExistedException {
		
		String partnerID = partnerDomain.register(username, password);
		
		return RepresentationConverter.toPartnerRepresentation(partnerID, username);
	}
	
	public PartnerInfoRepresentation addPartnerInfo(String partnerID, String name, String address) throws NotExistException, AlreadyExistedException {
		String partnerInfoID = partnerDomain.addPartnerInfo(partnerID, name, address);
		
		return RepresentationConverter.toPartnerInfoRepresentation(partnerID, name, address);
	}
	
	public PartnerRepresentation login(String username, String password) throws NotExistException {
		Partner partner = partnerDomain.login(username, password);
		
		return RepresentationConverter.toPartnerRepresentation(partner.getPartnerID(), partner.getUsername());
	}
	
	public PartnerInfoRepresentation getPartnerInfo(String partnerID) throws NotExistException {
		PartnerInfo partnerInfo = partnerDomain.getPartnerInfo(partnerID);
		
		return RepresentationConverter.toPartnerInfoRepresentation(partnerInfo.getPartnerID(), partnerInfo.getName(), partnerInfo.getAddress());
	}
	
	public List<BookRepresentation> getPartnerBooks(String partnerID) throws NotExistException {
		PartnerInfo info = partnerDomain.getPartnerInfo(partnerID);
		List<Book> books = info.getBooks();
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book)).collect(Collectors.toList());
		
	}
	
	public void deleteBook(String partnerID, String bookID) throws NotExistException, UnAuthorizedException {
		partnerDomain.deleteBook(partnerID, bookID);
	}

}
