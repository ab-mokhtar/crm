// KafkaTemplateListener.java
package com.mosofty.chat.kafka;

import com.mosofty.chat.entity.Message;
import com.mosofty.chat.services.MessageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RequiredArgsConstructor
public class KafkaTemplateListener implements MessageListener<String, Message> {
    private  SimpMessagingTemplate template;


    public KafkaTemplateListener(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void onMessage(ConsumerRecord<String, Message> record) {
        System.out.println("RECORD PROCESSING: " + record);

        template.convertAndSend("/topic/" + record.value().getSource(), record.value());
        template.convertAndSend("/topic/" + record.value().getDestination(), record.value());
    }
}
