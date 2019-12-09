package service.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain_layer.partner.PartnerDomain;
import domain_layer.partner.PartnerDomainImpl;
import entity.book.Book;
import entity.customer.CustomerInfo;
import entity.partner.Partner;
import entity.partner.PartnerInfo;
import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookRepresentation;
import representation.CustomerInfoRepresentation;
import representation.CustomerInfoRequest;
import representation.Link;
import representation.PartnerInfoRepresentation;
import representation.PartnerInfoRequest;
import representation.PartnerRepresentation;
import service.util.RepresentationConverter;

public class PartnerServiceActivity {
	
	private static PartnerDomain partnerDomain = new PartnerDomainImpl();
	
	public PartnerRepresentation register(String username, String password) throws AlreadyExistedException {
		
		String partnerID = partnerDomain.register(username, password);
		
		return RepresentationConverter.toPartnerRepresentation(partnerID, username, this.linkForPartner(partnerID));
	}
	
	public PartnerInfoRepresentation addPartnerInfo(String partnerID, String name, String address) throws NotExistException, AlreadyExistedException {
		String partnerInfoID = partnerDomain.addPartnerInfo(partnerID, name, address);
		
		return RepresentationConverter.toPartnerInfoRepresentation(partnerID, name, address);
	}
	
	public PartnerRepresentation login(String username, String password) throws NotExistException {
		Partner partner = partnerDomain.login(username, password);
		
		return RepresentationConverter.toPartnerRepresentation(partner.getPartnerID(), partner.getUsername(), this.linkForPartner(partner.getPartnerID()));
	}
	
	public PartnerInfoRepresentation getPartnerInfo(String partnerID) throws NotExistException {
		PartnerInfo partnerInfo = partnerDomain.getPartnerInfo(partnerID);
		
		return RepresentationConverter.toPartnerInfoRepresentation(partnerInfo.getPartnerID(), partnerInfo.getName(), partnerInfo.getAddress());
	}
	
	public PartnerInfoRepresentation updatePartnerInfo(PartnerInfoRequest request) throws NotExistException, UnAuthorizedException {
		PartnerInfo info = partnerDomain.updatePartnerInfo(request.getPartnerID(), request.getName(), request.getAddress());
		
		return RepresentationConverter.toPartnerInfoRepresentation(info.getPartnerID(), info.getName(), info.getAddress());
	}
	
	public void deletePartner(String id) throws NotExistException {
		partnerDomain.deleteCustomer(id);
	}
	
	private List<Link> linkForPartner(String partnerID) {
		List<Link> links = new ArrayList<>();
		
		Link book = new Link();
		book.setRel("books");
		book.setUrl("http://localhost:8080/bookstore/v1/partners/" + partnerID + "/books");
		book.setMediaType("application/json");
		
		Link order = new Link();
		order.setRel("orders");
		order.setUrl("http://localhost:8080/bookstore/v1/partners/" + partnerID + "/orders");
		order.setMediaType("application/json");
		
		Link addBook = new Link();
		addBook.setRel("add_book");
		addBook.setUrl("http://localhost:8080/bookstore/v1/books/");
		addBook.setMediaType("application/json");
		
		Link delete = new Link();
		delete.setRel("delete_order");
		delete.setUrl("http://localhost:8080/bookstore/v1/partners/" + partnerID);
		delete.setMediaType("application/json");
		
		Link addPartnerInfo = new Link();
		addPartnerInfo.setRel("add_partner_info");
		addPartnerInfo.setUrl("http://localhost:8080/bookstore/v1/partners/" + partnerID);
		addPartnerInfo.setMediaType("application/json");
		
		Link updatePartnerInfo = new Link();
		updatePartnerInfo.setRel("update_partner_info");
		updatePartnerInfo.setUrl("http://localhost:8080/bookstore/v1/partners/" + partnerID);
		updatePartnerInfo.setMediaType("application/json");
		
		Link self = new Link();
		self.setRel("self");
		self.setUrl("http://localhost:8080/bookstore/v1/partners/" + partnerID);
		self.setMediaType("application/json");
		
		links.add(book);
		links.add(order);
		links.add(addBook);
		links.add(delete);
		links.add(addPartnerInfo);
		links.add(updatePartnerInfo);
		links.add(self);
		
		return links;
	}

}
