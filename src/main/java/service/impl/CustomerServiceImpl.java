package service.impl;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookReviewRepresentation;
import representation.CustomerInfoRepresentation;
import representation.CustomerInfoRequest;
import representation.CustomerRepresentation;
import representation.CustomerRequest;
import representation.PartnerInfoRepresentation;
import representation.PartnerRepresentation;
import service.CustomerService;
import service.workflow.CustomerServiceActivity;
import service.workflow.PartnerServiceActivity;

@Path("/customers")
public class CustomerServiceImpl implements CustomerService {

	@Override
	public Response createCustomer(CustomerRequest request) {
		try {
			CustomerRepresentation representation = new CustomerServiceActivity().register(request.getUsername(), request.getPassword());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(AlreadyExistedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response createCustomerInfo(CustomerInfoRequest request) {
		try {
			CustomerInfoRepresentation representation = new CustomerServiceActivity().addCustomerInfo(request.getCustomerID(), request.getName(), request.getAddress());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(AlreadyExistedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response login(CustomerRequest request) {
		try {
			CustomerRepresentation representation = new CustomerServiceActivity().login(request.getUsername(), request.getPassword());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response getCustomerInfo(String id) {
		try {
			CustomerInfoRepresentation representation = new CustomerServiceActivity().getCustomerInfo(id);
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response getCustomerReviews(String id) {
		try {
			List<BookReviewRepresentation> representation = new CustomerServiceActivity().getBookReviews(id);
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response deleteReview(String id, String reviewID) {
		try {
			new CustomerServiceActivity().deleteReview(id, reviewID);
			return Response.status(Response.Status.OK).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response updateCustomerInfo(CustomerInfoRequest request) {
		try {
			return Response.status(Response.Status.OK).entity(new CustomerServiceActivity().updateCustomerInfo(request)).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
		}
	}

}
