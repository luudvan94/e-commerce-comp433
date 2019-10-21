package service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import exception.NotExistException;
import representation.BookDeleteRequest;
import representation.OrderRequest;
import service.OrderService;
import service.workflow.OrderServiceActivity;
import service.workflow.PaymentServiceActivity;

@Path("/orders")
public class OrderServiceImpl implements OrderService {

	@Override
	public Response get(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().get(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
			
		}
	}

	@Override
	public Response ordersByCustomer(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response ordersByPartner(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response createNewOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteOrder(BookDeleteRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
