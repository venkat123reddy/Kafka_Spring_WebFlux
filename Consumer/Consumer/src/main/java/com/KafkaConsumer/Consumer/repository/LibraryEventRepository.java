package com.KafkaConsumer.Consumer.repository;

import com.KafkaConsumer.Consumer.libraryEvents.LibraryEvents;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryEventRepository extends MongoRepository<LibraryEvents,String> {
}
