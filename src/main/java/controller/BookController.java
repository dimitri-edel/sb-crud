package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Book;
import repo.BookRepository;

@RestController
public class BookController {
	@Autowired
	private BookRepository bookRepo;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> books = new ArrayList<Book>();
			bookRepo.findAll().forEach(books::add);
			if (books.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(books);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable long id) {
		Optional<Book> book = bookRepo.findById(id);

		if (book.isPresent()) {
			return ResponseEntity.ok(book.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/books/add")
	public void addBook(Book book) {

	}

	@PostMapping("/books/update")
	public void updateBook(Book book) {

	}

	@DeleteMapping("/books/delete")
	public void deleteBook(long id) {

	}
}
