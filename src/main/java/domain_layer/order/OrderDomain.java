package domain_layer.order;

import java.util.List;

import entity.order.Order1;
import entity.order.Order_Book;
import exception.NotExistException;
import exception.UnAuthorizedException;

public interface OrderDomain {
	
	Order1 addOrder(String customerID, String shippingAddress, double total, String paymentID) throws NotExistException;
	
	Order_Book addOrderBook(String bookID, int quantity, double total, Order1 order) throws NotExistException;
	
//	List<String> addOrderDetail(List<Order_Book> orderBooks);
	
	Order1 shippingOrder(String id) throws NotExistException, UnAuthorizedException;
	
	Order1 cancellingOrder(String id) throws NotExistException, UnAuthorizedException;
	
	Order1 orderDelivered(String id) throws NotExistException, UnAuthorizedException;
	
	Order1 updateShippingAddress(String orderID, String shippingAddress) throws NotExistException;
	
	String getStatusByOrder(String id) throws NotExistException;
	
	List<Order_Book> getOrderDetailByOrder(String id);
	
	Order1 getOrder(String id) throws NotExistException;

}
