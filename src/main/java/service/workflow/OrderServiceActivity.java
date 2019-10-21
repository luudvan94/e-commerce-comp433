package service.workflow;

import domain_layer.order.OrderDomain;
import domain_layer.order.OrderDomainImpl;
import entity.order.Order1;
import exception.NotExistException;
import representation.OrderRepresentation;
import service.util.RepresentationConverter;

public class OrderServiceActivity {
	
	private OrderDomain orderDomain = new OrderDomainImpl();
	
	public OrderRepresentation get(String id) throws NotExistException {
		Order1 order = orderDomain.getOrder(id);
		
		return RepresentationConverter.toOrderRepresentation(order);
		
	}

}
