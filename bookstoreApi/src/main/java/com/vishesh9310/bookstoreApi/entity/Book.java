package com.vishesh9310.bookstoreApi.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String title;
    private String content;

    @NonNull
    private BigDecimal price;
}
