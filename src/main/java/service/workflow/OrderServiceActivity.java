package service.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain_layer.customer.CustomerDomainImpl;
import domain_layer.order.OrderDomain;
import domain_layer.order.OrderDomainImpl;
import domain_layer.partner.PartnerDomainImpl;
import entity.customer.CustomerInfo;
import entity.order.Order1;
import entity.order.Order_Book;
import entity.partner.PartnerInfo;
import exception.NotExistException;
import exception.UnAuthorizedException;
import representation.Link;
import representation.OrderBookRepresentation;
import representation.OrderInfoRequest;
import representation.OrderRepresentation;
import representation.OrderRequest;
import representation.OrderStatus;
import service.util.RepresentationConverter;

public class OrderServiceActivity {

	private OrderDomain orderDomain = new OrderDomainImpl();

	public OrderRepresentation get(String id) throws NotExistException {
		Order1 order = orderDomain.getOrder(id);

		return RepresentationConverter.toOrderRepresentation(order, this.linkForOrder(order.getOrderID()));

	}

	public OrderRepresentation createNewOrder(OrderRequest request) throws NotExistException {
		Order1 order = orderDomain.newOrder();

		List<Order_Book> orderBooks = new ArrayList();
		orderBooks.add(orderDomain.addOrderBook(request.getBookID(), request.getQty(), request.getTotal(), order));

		order.setProducts(orderBooks);

		return RepresentationConverter.toOrderRepresentation(order, this.linkForOrder(order.getOrderID()));
	}

	public OrderRepresentation fullfilOrder(OrderInfoRequest request, String id) throws NotExistException {
		Order1 order = orderDomain.updateOrder(id, request.getCustomerID(), request.getShippingAddress(),
				request.getBillingAddress(), request.getTotal());

		return RepresentationConverter.toOrderRepresentation(order, this.linkForOrder(order.getOrderID()));
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
		CustomerInfo customerInfo = new CustomerDomainImpl().getCustomerInfo(id);

		List<Order1> orders = orderDomain.getOrderByCustomerInfo(customerInfo.getCustomerInfoID());

		return orders.stream().map(order -> RepresentationConverter.toOrderRepresentation(order, this.linkForOrder(order.getOrderID())))
				.collect(Collectors.toList());
	}

	public List<OrderBookRepresentation> getOrderByPartnerID(String id) throws NotExistException {

		PartnerInfo partnerInfo = new PartnerDomainImpl().getPartnerInfo(id);

		List<Order_Book> orders = orderDomain.getOrderByPartnerInfo(partnerInfo.getPartnerInfoID());

		return orders.stream().map(order -> RepresentationConverter.toOrderBookRepresentaiton(order.getBook(),
				order.getQty(), order.getTotal(), order.getOrder().getStatus())).collect(Collectors.toList());
	}

	public List<OrderBookRepresentation> getOrdersByPartnerInfoByStatus(String id, String status)
			throws NotExistException {

		if (!status.equalsIgnoreCase("pending") && !status.equalsIgnoreCase("shipping")
				&& !status.equalsIgnoreCase("cancelled") && !status.equalsIgnoreCase("delivered")) {
			throw new NotExistException("Not supported status");
		}

		PartnerInfo partnerInfo = new PartnerDomainImpl().getPartnerInfo(id);

		List<Order_Book> orders = orderDomain.getOrderByPartnerInfo(partnerInfo.getPartnerInfoID());

		orders = orders.stream().filter(order -> order.getOrder().getStatus().equalsIgnoreCase(status))
				.collect(Collectors.toList());

		if (orders.size() == 0) {
			throw new NotExistException("No order connect to partner info id by status");
		}

		return orders.stream().map(order -> RepresentationConverter.toOrderBookRepresentaiton(order.getBook(),
				order.getQty(), order.getTotal(), order.getOrder().getStatus())).collect(Collectors.toList());
	}

	public List<Link> linkForOrder(String orderID) {
		List<Link> links = new ArrayList<>();

		Link self = new Link();
		self.setRel("self");
		self.setUrl("http://localhost:8080/bookstore/v1/orders/" + orderID);
		self.setMediaType("application/json");
		
		
		Link status = new Link();
		status.setRel("status");
		status.setUrl("http://localhost:8080/bookstore/v1/orders/" + orderID + "/status");
		status.setMediaType("application/json");
		
		Link shipping = new Link();
		shipping.setRel("shipping");
		shipping.setUrl("http://localhost:8080/bookstore/v1/orders/" + orderID + "/shipping");
		shipping.setMediaType("application/json");
		
		Link cancel = new Link();
		cancel.setRel("cancel");
		cancel.setUrl("http://localhost:8080/bookstore/v1/orders/" + orderID + "/cancel");
		cancel.setMediaType("application/json");
		
		Link delivered = new Link();
		delivered.setRel("delivered");
		delivered.setUrl("http://localhost:8080/bookstore/v1/orders/" + orderID + "/delivered");
		delivered.setMediaType("application/json");

		links.add(self);
		links.add(status);
		links.add(shipping);
		links.add(cancel);
		links.add(delivered);

		return links;
	}

}
