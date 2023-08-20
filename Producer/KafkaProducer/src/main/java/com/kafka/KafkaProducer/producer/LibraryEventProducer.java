package com.kafka.KafkaProducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.KafkaProducer.domain.LibraryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LibraryEventProducer {

    public final Logger logger = LoggerFactory.getLogger(LibraryEventProducer.class);
    private final KafkaTemplate<Integer,String> kafkaTemplate;


    @Value("${spring.kafka.topic}")
    String topicName;

    private final ObjectMapper objectMapper;

    public LibraryEventProducer(KafkaTemplate<Integer, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<SendResult<Integer, String>> publishLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        var key = libraryEvent.getId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        return kafkaTemplate.send(topicName,key,value)
                .whenComplete((message,error)->
                {
                   if(error!=null) {
                       logger.info("Published Successfully");
                   } else {
                       logger.error("published failed"+error.getMessage());
                   }
                });



    }


}
