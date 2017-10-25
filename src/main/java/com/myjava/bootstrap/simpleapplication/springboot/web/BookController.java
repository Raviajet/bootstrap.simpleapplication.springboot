package com.myjava.bootstrap.simpleapplication.springboot.web;


import com.myjava.bootstrap.simpleapplication.springboot.persistance.model.Book;
import com.myjava.bootstrap.simpleapplication.springboot.persistance.repo.BookRepository;
import com.myjava.bootstrap.simpleapplication.springboot.web.exception.BookIdMismatchException;
import com.myjava.bootstrap.simpleapplication.springboot.web.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List<Book> findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        final Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        final Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new BookNotFoundException();
        }
        bookRepository.delete(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        final Book old = bookRepository.findOne(id);
        if (old == null) {
            throw new BookNotFoundException();
        }
        return bookRepository.save(book);
    }

}