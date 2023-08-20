package com.KafkaConsumer.Consumer.libraryEvents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collation = "libraryEvent")
public class LibraryEvents {

    @Id
    private Integer id;
    private String libraryEventType;
    private Book book;
}
