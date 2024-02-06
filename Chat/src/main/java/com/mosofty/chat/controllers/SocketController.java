package com.mosofty.chat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosofty.chat.entity.Message;

import com.mosofty.chat.kafka.KafkaListenerCreator;
import com.mosofty.chat.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class SocketController {
    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;

    @Autowired
    KafkaListenerCreator kafkaListenerCreator;
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;
    private final MessageService messageService;



    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Message message) {
        try {

            message.setDate(LocalDateTime.now());
            System.err.println("Message : " + message);
            messageService.AddMessages(message);
            kafkaListenerCreator.createAndRegisterListener(message.getDestination().replace("@", "a"));
            kafkaListenerCreator.createAndRegisterListener(message.getSource().replace("@", "a"));
            kafkaTemplate.send(message.getDestination().replace("@", "a"), message);
//            template.convertAndSend("/topic/"+message.getSource(), message);
//            template.convertAndSend("/topic/"+message.getDestination(), message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





