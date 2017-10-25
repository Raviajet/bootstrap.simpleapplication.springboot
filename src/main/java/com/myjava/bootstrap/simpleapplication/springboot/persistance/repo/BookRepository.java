package com.myjava.bootstrap.simpleapplication.springboot.persistance.repo;


import java.util.List;
import com.myjava.bootstrap.simpleapplication.springboot.persistance.model.Book;
 import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}