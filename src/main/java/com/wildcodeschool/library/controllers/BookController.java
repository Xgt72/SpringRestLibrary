package com.wildcodeschool.library.controllers;

import com.wildcodeschool.library.models.Book;
import com.wildcodeschool.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book) {
        Book bookToUpdate = bookRepository.findById(id).get();
        if(book.getTitle() != null) {
            bookToUpdate.setTitle(book.getTitle());
        }
        if(book.getAuthor() != null) {
            bookToUpdate.setAuthor(book.getAuthor());
        }
        if(book.getDescription() != null) {
            bookToUpdate.setDescription(book.getDescription());
        }
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable int id) {
        bookRepository.deleteById(id);
        return true;
    }


}
