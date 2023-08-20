package com.KafkaConsumer.Consumer.libraryService;

import com.KafkaConsumer.Consumer.libraryEvents.LibraryEvents;
import com.KafkaConsumer.Consumer.repository.LibraryEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class LibraryEventService {
    final LibraryEventRepository libraryEventRepository;
    final ObjectMapper objectMapper;

    @Autowired
    public  LibraryEventService(LibraryEventRepository libraryEventRepository,ObjectMapper objectMapper) {
        this.libraryEventRepository = libraryEventRepository;
        this.objectMapper = objectMapper;
    }

    public void process(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        LibraryEvents libraryEvent = objectMapper.readValue(consumerRecord.value(),LibraryEvents.class);
        log.info("Conversion... done");
        libraryEventRepository.save(libraryEvent);
    }
}
