package domain_layer.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.customer.CustomerInfoRepositoryImpl;
import dal.order.OrderRepository;
import dal.order.OrderRepositoryImpl;
import dal.order.Order_BookRepository;
import dal.order.Order_BookRepositoryImpl;
import dal.payment.PaymentRepositoryImpl;
import entity.book.Book;
import entity.customer.CustomerInfo;
import entity.order.Order1;
import entity.order.Order_Book;
import entity.payment.Payment;
import exception.NotExistException;
import exception.UnAuthorizedException;
import util.ID;

public class OrderDomainImpl implements OrderDomain {
	
	private OrderRepository orderRepository = new OrderRepositoryImpl();
	private Order_BookRepository order_bookRepository = new Order_BookRepositoryImpl();

	@Override
	public Order1 addOrder(String customerID, String shippingAddress,
			double total, String paymentID) throws NotExistException {
		CustomerInfo info = new CustomerInfoRepositoryImpl().customerInfoByCustomerID(customerID);
		Payment payment = new PaymentRepositoryImpl().get(paymentID);
		
		Order1 order = new Order1();
		order.setCustomerInfo(info);
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setDate_updated(Long.toString(new Date().getTime()));
		order.setTotal(total);
		order.setStatus("pending");
		order.setOrderID(ID.generateID("O"));
		
		orderRepository.create(order);
		
		return order;
	}

	@Override
	public Order1 shippingOrder(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = this.getOrder(id);
		
		if (order.getStatus() == "cancelled") {
			throw new UnAuthorizedException("Order can not be set cancelled by this stage");
		}
		
		if (order.getStatus() == "shipping") {
			throw new UnAuthorizedException("Order is already in shipping stage");
		}
		
		if (order.getStatus() == "delivered") {
			throw new UnAuthorizedException("Order is already delivered");
		}
		
		order.setStatus("shipping");
		
		orderRepository.update(order);
		
		return order;
	}

	@Override
	public Order1 cancellingOrder(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = this.getOrder(id);
		
		if (order.getStatus() == "shipping") {
			throw new UnAuthorizedException("Order is already shipping");
		}
		
		if (order.getStatus() == "cancelled") {
			throw new UnAuthorizedException("Order is already in cancelled stage");
		}
		
		if (order.getStatus() == "delivered") {
			throw new UnAuthorizedException("Order is already delivered");
		}
		
		
		order.setStatus("cancelled");
		
		orderRepository.update(order);
		
		return order;
	}
	
	@Override
	public Order1 orderDelivered(String id) throws NotExistException, UnAuthorizedException {
		Order1 order = this.getOrder(id);
		
		if (order.getStatus() == "cancelled") {
			throw new UnAuthorizedException("Order can not be set delivered by this stage");
		}
		
		if (order.getStatus() == "delivered") {
			throw new UnAuthorizedException("Order is already delivered");
		}
		
		order.setStatus("delivered");
		
		orderRepository.update(order);
		
		return order;
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
	public Order1 getOrder(String id) throws NotExistException {
		Order1 order = orderRepository.get(id);
		
		if(order == null) {
			throw new NotExistException("Order with provided id does not exist");
		}
		
		return order;
	}

	@Override
	public Order_Book addOrderBook(String bookID, int quantity, double total, Order1 order) throws NotExistException {
		Book book = new BookRepositoryImpl().get(bookID);
		
		Order_Book orderBook = new Order_Book();
		orderBook.setBook(book);
		orderBook.setQty(quantity);
		orderBook.setTotal(total);
		orderBook.setOrder(order);
		orderBook.setId(ID.generateID("OB"));
		
		order_bookRepository.create(orderBook);
		return orderBook;
	}

	@Override
	public Order1 updateShippingAddress(String orderID, String shippingAddress) throws NotExistException {
		Order1 order = this.getOrder(orderID);
		
		order.setShippingAddress(shippingAddress);
		
		orderRepository.update(order);
		
		return order;
	}

}
