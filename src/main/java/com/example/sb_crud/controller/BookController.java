package com.example.sb_crud.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sb_crud.model.Book;
import com.example.sb_crud.repo.BookRepository;

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
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book obj = bookRepo.save(book);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	@PostMapping("/books/update")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		Optional<Book> bookData = bookRepo.findById(book.getId());
		if (bookData.isPresent()) {
			Book obj = bookData.get();
			obj.setTitle(book.getTitle());
			obj.setAuthor(book.getAuthor());
			bookRepo.save(obj);
			return new ResponseEntity<>(obj, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/books/delete/{id}")
	public ResponseEntity<HttpStatus> deleteBookById(@PathVariable long id) {
		if (bookRepo.existsById(id)) {
			bookRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
