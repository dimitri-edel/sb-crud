package com.example.sb_crud.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.sb_crud.controller.BookController;
import com.example.sb_crud.model.Book;
import com.example.sb_crud.repo.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
class TestBookController {
	@Autowired
	MockMvc mockMvc;
	
	// Create objects to convert Java objects to JSON and vice versa
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	// Create a mock BookRepository object
	@Mock
	BookRepository bookRepository;
	// Create a BookController object and inject the mock BookRepository object
	@InjectMocks
	BookController bookController;
	// Create Test Data
	Book book1 = new Book(1, "Java Programming", "John Doe");
	Book book2 = new Book(2, "Python Programming", "Jane Doe");
	Book book3 = new Book(3, "C Programming", "James Doe");
	
	@BeforeEach
	void setUp() throws Exception {
		// Ensure that Mockito annotations are initialized and the mockMvc object is created
		// so that a mock of the BookController object can be used for testing		
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	@Test
    void testGetAllBooksSuccess() throws Exception {
        // Create a list of books and add data to the list
        List<Book> books = new ArrayList<>(Arrays.asList(book1, book2, book3));
        // Mock repository
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        // Emulate a GET request to the /books endpoint
        // Verify that the response status is 200 OK
        // Verify that the response has a size of 3
        // Verify that the title of the third book is "C Programming"
        mockMvc.perform(MockMvcRequestBuilders
            .get("/books")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[2].title", is("C Programming")));
    }
}
