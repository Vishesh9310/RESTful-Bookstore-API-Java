package com.vishesh9310.bookstoreApi.repository;

import com.vishesh9310.bookstoreApi.entity.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, ObjectId>{
    Book findByTitle(String title);
}
