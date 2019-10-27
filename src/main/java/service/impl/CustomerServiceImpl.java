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
import service.workflow.BookReviewServiceActivity;
import service.workflow.CustomerServiceActivity;
import service.workflow.OrderServiceActivity;
import service.workflow.PartnerServiceActivity;

@Path("/v1/customers")
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
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response login(CustomerRequest request) {
		try {
			CustomerRepresentation representation = new CustomerServiceActivity().login(request.getUsername(), request.getPassword());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response getCustomerInfo(String id) {
		try {
			CustomerInfoRepresentation representation = new CustomerServiceActivity().getCustomerInfo(id);
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response updateCustomerInfo(CustomerInfoRequest request) {
		try {
			return Response.status(Response.Status.OK).entity(new CustomerServiceActivity().updateCustomerInfo(request)).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response reviewsByCustomerID(String id) {
		try {
			List<BookReviewRepresentation> representation = new BookReviewServiceActivity().getBookReviews(id);
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build(); 
		}
	}

	@Override
	public Response ordersByCustomer(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().getOrderByCustomerInfo(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		}
	}

}
