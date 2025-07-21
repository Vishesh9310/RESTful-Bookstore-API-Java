package com.vishesh9310.bookstoreApi.services;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.repository.AuthorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public void saveEntry(Author author){
        authorRepository.save(author);
    }

    public void deleteByName(String name){
        if(authorRepository.findByName(name) != null){
            Author author = authorRepository.findByName(name);
            authorRepository.deleteById(author.getId());
        }
    }

    public void deleteById(ObjectId id){
        authorRepository.deleteById(id);
    }

    public Author findByName(String name){
        return authorRepository.findByName(name);
    }

    public Optional<Author> findById(ObjectId id){
        return authorRepository.findById(id);
    }

}
