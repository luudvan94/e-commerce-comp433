package service.workflow;

import representation.BookRepresentation;
import representation.BookReviewRepresentation;
import representation.ObjectFactory;
import service.exception.NoContentException;
import service.exception.NotExistException;
import service.util.RepresentationConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dal.book.BookRepository;
import dal.book.BookRepositoryImpl;
import dal.book_review.BookReviewRepository;
import dal.book_review.BookReviewRepositoryImpl;
import domain.book.Book;
import domain.book_review.BookReview;

public class BookServiceActivity {
	
	private static BookRepository bo = new BookRepositoryImpl();
	private static BookReviewRepository bRe = new BookReviewRepositoryImpl();
	
	public BookRepresentation get(String id) throws NotExistException {
		Book book = bo.get(id);
		
		if (book == null) {
			throw new NotExistException("Book is not existed with provided ID");
		}
		
		return RepresentationConverter.toBookRepresentation(book);
	}
	
	public List<BookRepresentation> getBooksByTitle(String title) throws NoContentException {
		List<Book> books = bo.booksByTitle(title);
		
		if (books.size() == 0) {
			throw new NoContentException("Can't find any book with provided title");
		}
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book)).collect(Collectors.toList());
	}
	
	public List<BookRepresentation> getAll() throws NoContentException {
		List<Book> books = bo.getAll();
		
		if (books.size() == 0) {
			throw new NoContentException("Can't find any book");
		}
		
		return books.stream().map(book -> RepresentationConverter.toBookRepresentation(book)).collect(Collectors.toList());
	}
	
	public List<BookReviewRepresentation> getReviewsByBookID(String id) throws NoContentException {
		List<BookReview> bookReviews = bRe.bookReviewsByBookID(id);
		System.out.println(id);
		
		if (bookReviews == null || bookReviews.size() == 0) {
			throw new NoContentException("Can't find any book review with provided book id");
		}
		
		System.out.println(bookReviews.size());
		
		return bookReviews.stream().map(bookReview -> RepresentationConverter.toBookReviewRepresentation(bookReview)).collect(Collectors.toList());
	}

}
