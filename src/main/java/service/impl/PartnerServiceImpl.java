package service.impl;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import exception.AlreadyExistedException;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookRepresentation;
import representation.PartnerInfoRepresentation;
import representation.PartnerInfoRequest;
import representation.PartnerRepresentation;
import representation.PartnerRequest;
import service.PartnerService;
import service.workflow.CustomerServiceActivity;
import service.workflow.PartnerServiceActivity;

@Path("/partners")
public class PartnerServiceImpl implements PartnerService {

	@Override
	public Response createPartner(PartnerRequest request) {
		try {
			PartnerRepresentation representation = new PartnerServiceActivity().register(request.getUsername(), request.getPassword());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(AlreadyExistedException ex) {
			return Response.status(Response.Status.CONFLICT).build(); 
		}
	}

	@Override
	public Response createPartnerInfo(PartnerInfoRequest request) {
		
		try {
			PartnerInfoRepresentation representation = new PartnerServiceActivity().addPartnerInfo(request.getPartnerID(), request.getName(), request.getAddress());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(AlreadyExistedException ex) {
			return Response.status(Response.Status.CONFLICT).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Override
	public Response login(PartnerRequest request) {
		try {
			PartnerRepresentation representation = new PartnerServiceActivity().login(request.getUsername(), request.getPassword());
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build(); 
		}
	}

	@Override
	public Response getPartnerInfo(String id) {
		try {
			PartnerInfoRepresentation representation = new PartnerServiceActivity().getPartnerInfo(id);
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build(); 
		}
	}

	@Override
	public Response getPartnerBooks(String id) {
		try {
			List<BookRepresentation> representation = new PartnerServiceActivity().getPartnerBooks(id);
			return Response.status(Response.Status.OK).entity(representation).build();
			
		} catch(NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).build(); 
		}
	}

	@Override
	public Response deleteBook(String id, String bookID) {
		try {
			new PartnerServiceActivity().deleteBook(id, bookID);
			return Response.status(Response.Status.OK).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response updatePartnerInfo(PartnerInfoRequest request) {
		try {
			new PartnerServiceActivity().updatePartnerInfo(request);
			return Response.status(Response.Status.OK).build();
			
		} catch(UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build(); 
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
		}
	}

}
