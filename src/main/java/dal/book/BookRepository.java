package dal.book;

import java.util.List;

import dal.Repository;
import domain.book.Book;

public interface BookRepository extends Repository<Book, String> {

	List<Book> booksByTitle(String title);
	
	List<Book> booksByPartnerID(String id);
	
	List<Book> booksByIDList(List<String> idList);
}
