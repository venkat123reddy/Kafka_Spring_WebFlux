package com.KafkaConsumer.Consumer.consumer;

import com.KafkaConsumer.Consumer.libraryService.LibraryEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventConsumer {

    @Autowired
    LibraryEventService libraryEventService;

    @KafkaListener(topics={"library-events"})
    public void message(ConsumerRecord<Integer,String> consumerRecord)
    {
        log.info("consumer record : {} ",consumerRecord);
        try {
            libraryEventService.process(consumerRecord);
        }
        catch (Exception e) {
            log.info(e.getMessage());
    }
    }
}
