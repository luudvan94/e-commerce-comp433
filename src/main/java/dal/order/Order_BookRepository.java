package dal.order;

import java.util.List;

import dal.Repository;
import entity.order.Order_Book;

public interface Order_BookRepository extends Repository<Order_Book, String> {

	List<Order_Book> byOrderID(String id);
	List<Order_Book> byBookID(String id);
}
