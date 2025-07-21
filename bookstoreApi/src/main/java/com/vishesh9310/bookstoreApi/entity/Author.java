package com.vishesh9310.bookstoreApi.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String name;
    @NonNull
    private String password;
    @DBRef
    private List<Book> booklist = new ArrayList<>();
}
