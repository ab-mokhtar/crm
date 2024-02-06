package com.mosofty.chat.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topicPattern = "firsTopic", groupId = "myGroup")
    public void consumeMsg (String msg) {

        log.info(format("Consuming the message from producer Topic:: %s", msg));
    }

}
