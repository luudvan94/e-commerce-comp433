package domain_layer.order;

import java.util.List;

import entity.order.Order1;
import entity.order.Order_Book;
import exception.NotExistException;
import exception.UnAuthorizedException;

public interface OrderDomain {
	
	String addOrder(String customerID, String dateUpdated, String shippingAddressID, double total);
	
	List<String> addOrderDetail(List<Order_Book> orderBooks);
	
	void shippingOrder(String id) throws NotExistException, UnAuthorizedException;
	
	void cancellingOrder(String id) throws NotExistException, UnAuthorizedException;
	
	String getStatusByOrder(String id) throws NotExistException;
	
	List<Order_Book> getOrderDetailByOrder(String id);
	
	Order1 getOrder(String id) throws NotExistException;

}
