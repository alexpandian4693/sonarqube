package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Book;
import com.java.repository.BookDao;


@RestController
@RequestMapping(value="/springhibernateapi")
public class BookCtrl {

	@Autowired
	private BookDao bookdao;

	
	@PostMapping(value= "/create")
	public ResponseEntity<Book> create(@RequestBody Book book) {
		int id = bookdao.createBook(book);
		if(id != 0)
			return new ResponseEntity<Book>(HttpStatus.CREATED);

		return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value= "/getall")
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.ok(bookdao.findAll());
	}

	@GetMapping(value= "/get/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int bookid) {
		Book book = bookdao.findById(bookid);
		if(book == null)
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
}