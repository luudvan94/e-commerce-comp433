package repository;

import java.util.List;

import entity.Order_Book;

public interface Order_BookRepository extends Repository<Order_Book, String> {

	List<Order_Book> byOrderID(String id);
	List<Order_Book> byBookID(String id);
}
