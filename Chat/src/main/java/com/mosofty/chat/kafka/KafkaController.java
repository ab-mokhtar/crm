package com.mosofty.chat.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    KafkaListenerCreator kafkaListenerCreator;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam String topic) {
        kafkaListenerCreator.createAndRegisterListener(topic);
    }
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(path = "/send")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestParam String topic, @RequestParam String message) {
        kafkaTemplate.send(topic, message);
    }
}