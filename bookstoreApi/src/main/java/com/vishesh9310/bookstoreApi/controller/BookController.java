package com.vishesh9310.bookstoreApi.controller;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.entity.Book;
import com.vishesh9310.bookstoreApi.services.AuthorService;
import com.vishesh9310.bookstoreApi.services.BookService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{authorName}")
    public ResponseEntity<?> getAllBookOfAuthor(@PathVariable String authorName){
        Author author= authorService.findByName(authorName);
        List<Book> all = author.getBooklist();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book){
        bookService.saveEntry(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{authorName}")
    public ResponseEntity<Book> createEntry(@RequestBody Book myEntry, @PathVariable String authorName){
        try{
            bookService.saveEntry(myEntry,authorName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/title/{bookname}")
    public Book updateBook(@PathVariable String bookname, @RequestBody Book book ){
        Book bookInDB = bookService.findByTitle(bookname);
        if(bookInDB != null){
            if (book.getTitle() != null && !book.getTitle().isEmpty()) {
                bookInDB.setTitle(book.getTitle());
            }
            if (book.getPrice() != null) {
                bookInDB.setPrice(book.getPrice());
            }
            bookService.saveEntry(bookInDB);
            return bookInDB;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PutMapping("id/{authorName}/{myId}")
    public ResponseEntity<?> updateBookById(@PathVariable String authorName, @PathVariable ObjectId myId, @RequestBody Book newBook){
        Book old = bookService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(newBook.getTitle() != null && !newBook.getTitle().equals("") ? newBook.getTitle() : old.getTitle());
            old.setContent(newBook.getContent() != null && !newBook.getTitle().equals("") ? newBook.getContent() : old.getContent());
            old.setPrice(newBook.getPrice() != null && !newBook.getPrice().equals("") ? newBook.getPrice() : old.getPrice());
            bookService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteBookByTitle(@PathVariable String title){
        bookService.deleteByName(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable ObjectId id){
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("id/{authorName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String authorName, @PathVariable ObjectId myId){
        try{
            bookService.deleteById(myId, authorName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
