package service.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain_layer.order.OrderDomain;
import domain_layer.order.OrderDomainImpl;
import entity.order.Order1;
import entity.order.Order_Book;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.OrderBookRepresentation;
import representation.OrderRepresentation;
import representation.OrderRequest;
import representation.OrderStatus;
import service.util.RepresentationConverter;

public class OrderServiceActivity {
	
	private OrderDomain orderDomain = new OrderDomainImpl();
	
	public OrderRepresentation get(String id) throws NotExistException {
		Order1 order = orderDomain.getOrder(id);
		
		return RepresentationConverter.toOrderRepresentation(order);
		
	}
	
	public OrderRepresentation createNewOrder(OrderRequest request) throws NotExistException {
		Order1 order = orderDomain.addOrder(request.getCustomerID(), request.getShippingAddress(), request.getTotal(), request.getPaymentID());

		List<Order_Book> orderBooks = new ArrayList();
		for(OrderRequest.Products orderBook: request.getProducts() ) {
			orderBooks.add(orderDomain.addOrderBook(orderBook.getBookID(), orderBook.getQuantity(), orderBook.getTotal(), order));
		}
		
		order.setProducts(orderBooks);
		
		return RepresentationConverter.toOrderRepresentation(order);
	}
	
	public OrderStatus shippingOrder(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = orderDomain.shippingOrder(id);
		
		return RepresentationConverter.toOrderStatus(order);
	}
	
	public OrderStatus cancelOrder(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = orderDomain.cancellingOrder(id);
		
		return RepresentationConverter.toOrderStatus(order);
	}
	
	public OrderStatus orderDelivered(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = orderDomain.orderDelivered(id);
		
		return RepresentationConverter.toOrderStatus(order);
	}
	
	public OrderStatus getOrderStatus(String id) throws NotExistException {
		Order1 order = orderDomain.getOrder(id);
		
		return RepresentationConverter.toOrderStatus(order);
	}
	
	public List<OrderRepresentation> getOrderByCustomerInfo(String id) throws NotExistException {
		List<Order1> orders = orderDomain.getOrderByCustomerInfo(id);
		
		return orders.stream().map(order -> RepresentationConverter.toOrderRepresentation(order)).collect(Collectors.toList());
	}
	
	public List<OrderBookRepresentation> getOrderByPartnerInfo(String id) throws NotExistException {
		List<Order_Book> orders = orderDomain.getOrderByPartnerInfo(id);
		
		return orders.stream().map(order -> RepresentationConverter.toOrderBookRepresentaiton(order.getBook(), order.getQty(), order.getTotal(), order.getOrder().getStatus())).collect(Collectors.toList());
	}
	
	public List<OrderBookRepresentation> getOrdersByPartnerInfoByStatus(String id, String status) throws NotExistException {
		
		if (!status.equalsIgnoreCase("pending") && !status.equalsIgnoreCase("shipping") && !status.equalsIgnoreCase("cancelled") && !status.equalsIgnoreCase("delivered")) {
			throw new NotExistException("Not supported status");
		}
		
		List<Order_Book> orders = orderDomain.getOrderByPartnerInfo(id);
		
		orders = orders.stream().filter(order -> order.getOrder().getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		
		if(orders.size() == 0) {
			throw new NotExistException("No order connect to partner info id by status");
		}
		

		return orders.stream().map(order -> RepresentationConverter.toOrderBookRepresentaiton(order.getBook(), order.getQty(), order.getTotal(), order.getOrder().getStatus())).collect(Collectors.toList());
	}

}
