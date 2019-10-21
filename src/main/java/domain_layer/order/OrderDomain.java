package domain_layer.order;

import java.util.List;

import entity.order.Order1;
import entity.order.Order_Book;
import exception.NotExistException;
import exception.UnAuthorizedException;

public interface OrderDomain {
	
	String addOrder(String customerID, String dateUpdated, String shippingAddressID, double total) throws NotExistException;
	
	String addOrderBook(String bookID, int quantity, double total) throws NotExistException;
	
	List<String> addOrderDetail(List<Order_Book> orderBooks);
	
	void shippingOrder(String id) throws NotExistException, UnAuthorizedException;
	
	void cancellingOrder(String id) throws NotExistException, UnAuthorizedException;
	
	void orderDelivered(String id) throws NotExistException, UnAuthorizedException;
	
	Order1 updateShippingAddress(String orderID, String shippingAddress) throws NotExistException;
	
	String getStatusByOrder(String id) throws NotExistException;
	
	List<Order_Book> getOrderDetailByOrder(String id);
	
	Order1 getOrder(String id) throws NotExistException;

}
