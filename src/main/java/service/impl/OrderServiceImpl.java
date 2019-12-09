package service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.BookDeleteRequest;
import representation.OrderInfoRequest;
import representation.OrderRequest;
import service.OrderService;
import service.workflow.OrderServiceActivity;
import service.workflow.PaymentServiceActivity;

@Path("/v1/orders")
public class OrderServiceImpl implements OrderService {

	@Override
	public Response get(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().get(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		}
	}

	@Override
	public Response createNewOrder(OrderRequest request) {
		System.out.println("create order");
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().createNewOrder(request)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		}
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

	@Override
	public Response shippingOrder(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().shippingOrder(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		} catch (UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response cancellingOrder(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().cancelOrder(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		} catch (UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response orderDelivered(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().orderDelivered(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		} catch (UnAuthorizedException ex) {
			return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response getOrderStatus(String id) {
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().getOrderStatus(id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		}
	}

	@Override
	public Response fullfillOrder(OrderInfoRequest request, String id) {
		System.out.println(id);
		try {
			return Response.status(Response.Status.OK).entity(new OrderServiceActivity().fullfilOrder(request, id)).build();
			
		} catch (NotExistException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			
		}
	}


}
