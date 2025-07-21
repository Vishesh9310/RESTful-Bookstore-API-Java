package com.vishesh9310.bookstoreApi.services;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.entity.Book;
import com.vishesh9310.bookstoreApi.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public void saveEntry(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void saveEntry(Book book,String authorName){
        try{
            Author author = authorService.findByName(authorName);
            Book save = bookRepository.save(book);
            author.getBooklist().add(save);
            authorService.saveEntry(author);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void deleteByName(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book != null) {
            bookRepository.deleteById(book.getId());
        }
    }

    public void deleteById(ObjectId id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String authorName){
        Author author = authorService.findByName(authorName);
        author.getBooklist().removeIf(x -> x.getId().equals(id));
        authorService.saveEntry(author);
        bookRepository.deleteById(id);
    }

    public Optional<Book> findById(ObjectId id){
        return bookRepository.findById(id);
    }

    public Book findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

}
