package com.mosofty.chat.Configurations;

import com.mosofty.chat.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(id = "id-1", groupId = "group-1", topics = "Message-topic", containerFactory = "messageListenerFactory", autoStartup = "false")
    public void consumeMessage(Message message) {
        logger.info("Message received : -> {}", message);
    }
}
