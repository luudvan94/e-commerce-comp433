package dal.book;

import java.util.List;

import dal.Repository;
import entity.book.Book;

public interface BookRepository extends Repository<Book, String> {

	List<Book> booksByTitle(String title);
	
	List<Book> booksByPartnerInfoID(String id);
	
	List<Book> booksByIDList(List<String> idList);
}
