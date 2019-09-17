package repository;

import java.util.List;

import entity.Book;

public interface BookRepository extends Repository<Book, String> {

	List<Book> booksByTitle(String title);
	
	List<Book> booksByPartnerID(String id);
}
