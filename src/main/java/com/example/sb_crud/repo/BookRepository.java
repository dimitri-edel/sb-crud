package com.example.sb_crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sb_crud.model.Book;

// Create a JPA repository for the Book entity
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
