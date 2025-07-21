package com.vishesh9310.bookstoreApi.controller;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.services.AuthorService;
import com.vishesh9310.bookstoreApi.services.BookService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Author> getAllAuthor(){
        return authorService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody Author author){
        authorService.saveEntry(author);
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    @DeleteMapping("/{name}/{password}")
    public ResponseEntity<?> deleteByUserName(@PathVariable String name, @PathVariable String password){
        Author author = authorService.findByName(name);
        if(author != null && author.getPassword().equals(password)){
            authorService.deleteByName(name);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        Optional<Author> author = authorService.findById(id);
        if(author.isPresent()){
            authorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateAuthorByName(@PathVariable String name, @RequestBody Author newAuthor){
        Author old = authorService.findByName(name);
        if(old != null){
            old.setName(newAuthor.getName());
            old.setPassword(newAuthor.getPassword());
            authorService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getAuthorByName(@PathVariable String name){
        Author author = authorService.findByName(name);
        if(author != null){
            return new ResponseEntity<>(author,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
