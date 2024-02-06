package com.mosofty.chat.services;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaListenerAutomation {

    private final Logger logger = LoggerFactory.getLogger(KafkaListenerAutomation.class);
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public boolean startListener(String listenerId) {
     kafkaListenerEndpointRegistry.getAllListenerContainers().stream().forEach(x-> System.out.println(x.getContainerProperties()));
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(listenerId);
        assert listenerContainer != null : false;
        listenerContainer.start();
        logger.info("{} Kafka Listener Started", listenerId);
        return true;
    }

    public boolean stopListener(String listenerId) {
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(listenerId);
        assert listenerContainer != null : false;
        listenerContainer.stop();
        logger.info("{} Kafka Listener Stopped.", listenerId);
        return true;
    }
}
