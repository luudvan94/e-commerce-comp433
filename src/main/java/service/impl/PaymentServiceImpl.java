package service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import exception.NotExistException;
import representation.BookRepresentation;
import representation.PaymentRequest;
import service.PaymentService;
import service.workflow.BookServiceActivity;
import service.workflow.PaymentServiceActivity;

@Path("/payments")
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Response get(String id) {
//		System.out.println(id);
		try {
			return Response.status(Response.Status.OK).entity(new PaymentServiceActivity().get(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
			
		}
	}

	@Override
	public Response createNewBook(PaymentRequest request) {
		return Response.status(Response.Status.OK).entity(new PaymentServiceActivity().createNewPayment(request)).build();
	}

}
