package domain_layer.order;

import java.util.ArrayList;
import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.customer.CustomerInfoRepositoryImpl;
import dal.order.OrderRepository;
import dal.order.OrderRepositoryImpl;
import dal.order.Order_BookRepository;
import dal.order.Order_BookRepositoryImpl;
import entity.book.Book;
import entity.customer.CustomerInfo;
import entity.order.Order1;
import entity.order.Order_Book;
import exception.NotExistException;
import exception.UnAuthorizedException;
import util.ID;

public class OrderDomainImpl implements OrderDomain {
	
	private OrderRepository orderRepository = new OrderRepositoryImpl();
	private Order_BookRepository order_bookRepository = new Order_BookRepositoryImpl();

	@Override
	public String addOrder(String customerID, String dateUpdated, String shippingAddressID,
			double total) throws NotExistException {
		CustomerInfo info = new CustomerInfoRepositoryImpl().customerInfoByCustomerID(customerID);
		
		Order1 order = new Order1();
		order.setCustomerInfo(info);
		order.setShippingAddress(null);
		order.setDate_updated(dateUpdated);
		order.setTotal(total);
		order.setStatus("pending");
		order.setOrderID(ID.generateID("O"));
		
		return orderRepository.create(order);
	}

	@Override
	public void shippingOrder(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = this.getOrder(id);
		
		if (order.getStatus() == "cancelled" || order.getStatus() == "shipping") {
			throw new UnAuthorizedException("Order can not be set shipping by this stage");
		}
		
		order.setStatus("shipping");
		
		orderRepository.update(order);
	}

	@Override
	public void cancellingOrder(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = this.getOrder(id);
		
		if (order.getStatus() == "shipping" || order.getStatus() == "delivered") {
			throw new UnAuthorizedException("Order can not be set cancelled by this stage");
		}
		
		
		order.setStatus("cancelled");
		
		orderRepository.update(order);
		
	}
	
	@Override
	public void orderDelivered(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = this.getOrder(id);
		
		if (order.getStatus() == "cancelled") {
			throw new UnAuthorizedException("Order can not be set delivered by this stage");
		}
		
		if (order.getStatus() == "delivered") {
			throw new UnAuthorizedException("Order is already delivered");
		}
		
		order.setStatus("delivered");
		
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

	@Override
	public String addOrderBook(String bookID, int quantity, double total) throws NotExistException {
		Book book = new BookRepositoryImpl().get(bookID);
		
		Order_Book orderBook = new Order_Book();
		orderBook.setBook(book);
		orderBook.setQty(quantity);
		orderBook.setTotal(total);
		orderBook.setId(ID.generateID("OB"));
		
		return order_bookRepository.create(orderBook);
	}

	@Override
	public Order1 updateShippingAddress(String orderID, String shippingAddress) throws NotExistException {
		Order1 order = this.getOrder(orderID);
		
		order.setShippingAddress(shippingAddress);
		
		orderRepository.update(order);
		
		return order;
	}

}
