package com.vishesh9310.bookstoreApi.repository;

import com.vishesh9310.bookstoreApi.entity.Author;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AuthorRepository extends MongoRepository<Author, ObjectId>{
    Author findByName(String name);
}
