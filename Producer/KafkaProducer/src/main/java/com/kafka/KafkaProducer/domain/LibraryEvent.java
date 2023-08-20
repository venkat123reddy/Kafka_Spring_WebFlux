package com.kafka.KafkaProducer.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LibraryEvent {
    private Integer id;
    private String libraryEventType;
    private Book book;

}
