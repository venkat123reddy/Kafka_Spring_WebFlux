package com.kafka.KafkaProducer.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.KafkaProducer.domain.Book;
import com.kafka.KafkaProducer.domain.LibraryEvent;
import com.kafka.KafkaProducer.producer.LibraryEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryEventController {

    private final Logger logger = LoggerFactory.getLogger(LibraryEventController.class);
    private final LibraryEventProducer libraryEventProducer;

    public LibraryEventController(LibraryEventProducer libraryEventProducer) {
        this.libraryEventProducer = libraryEventProducer;
    }

    @PostMapping("/v1/libraryEvent")
    public ResponseEntity<LibraryEvent> post(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
        logger.info("LibraryEvent Controller");
        libraryEventProducer.publishLibraryEvent(libraryEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

    @GetMapping("v1/getLibrary")
    public  ResponseEntity<LibraryEvent> get() throws JsonProcessingException {
        LibraryEvent libraryEvent = new LibraryEvent();
        Book book = new Book();
        book.setId("1");
        book.setAuthor("alex");
        book.setName("Design");
        libraryEvent.setLibraryEventType("NEW");
        libraryEvent.setBook(book);
        libraryEvent.setId(1);
        libraryEventProducer.publishLibraryEvent(libraryEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
