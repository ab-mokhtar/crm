package com.mosofty.chat.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String,String>kafkaTemplate;
    public void sendMessage(String msg,String topic){
        log.info(String.format("sending message to firstCanal Topic :: %s",msg));
        kafkaTemplate.send(topic,msg);
    }
}
