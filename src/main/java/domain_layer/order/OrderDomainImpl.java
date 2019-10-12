package domain_layer.order;

import java.util.ArrayList;
import java.util.List;

import dal.order.OrderRepository;
import dal.order.OrderRepositoryImpl;
import dal.order.Order_BookRepository;
import dal.order.Order_BookRepositoryImpl;
import entity.order.Order1;
import entity.order.Order_Book;
import exception.NotExistException;
import util.ID;

public class OrderDomainImpl implements OrderDomain {
	
	private OrderRepository orderRepository = new OrderRepositoryImpl();
	private Order_BookRepository order_bookRepository = new Order_BookRepositoryImpl();

	@Override
	public String addOrder(String customerID, String dateUpdated, String shippingAddressID,
			double total) {
		Order1 order = new Order1();
		order.setCustomerID(customerID);
		order.setShippingAddressID(shippingAddressID);
		order.setDate_updated(dateUpdated);
		order.setTotal(total);
		order.setStatus("pending");
		order.setId(ID.generateID("O"));
		
		return orderRepository.create(order);
	}

	@Override
	public void shippingOrder(String id) throws NotExistException {
		Order1 order = this.getOrder(id);
		
		order.setStatus("shipping");
		
		orderRepository.update(order);
	}

	@Override
	public void cancellingOrder(String id) throws NotExistException {
		Order1 order = this.getOrder(id);
		
		order.setStatus("cancelled");
		
		orderRepository.update(order);
		
	}

	@Override
	public String getStatusByOrder(String id) throws NotExistException {
		Order1 order = this.getOrder(id);
		
		return order.getStatus();
	}

	@Override
	public List<Order_Book> getOrderDetailByOrder(String id) {
		return order_bookRepository.byOrderID(id);
	}

	@Override
	public List<String> addOrderDetail(List<Order_Book> orderBooks) {
		
		List<String> idList = new ArrayList<String>();
		
		for(Order_Book order_book: orderBooks) {
			String newId = order_bookRepository.create(order_book);
			idList.add(newId);
		}
		
		return idList;
		
	}

	@Override
	public Order1 getOrder(String id) throws NotExistException {
		Order1 order = orderRepository.get(id);
		
		if(order == null) {
			throw new NotExistException("Order with provided id does not exist");
		}
		
		return order;
	}

}
