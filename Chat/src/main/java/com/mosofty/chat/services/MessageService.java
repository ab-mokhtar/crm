package com.mosofty.chat.services;

import com.mosofty.chat.entity.Key;
import com.mosofty.chat.entity.Message;
import com.mosofty.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
public class MessageService {
    private final MessageRepository messageRepository;
    public List<Message> getDiscusions(String user){
        List<Message>msg=messageRepository.findMessageBySourceOrDestination(user,user);
        Map<Key,Message>discussions=msg.stream()
                .collect(Collectors.toMap(
                        projection -> new Key(projection.getSource(), projection.getDestination()),
                        projection -> projection, // Identity function, keep the original message
                        (existing, replacement) ->
                                existing.getDate().isAfter(replacement.getDate()) ? existing : replacement
                ));
        return discussions.values().stream().sorted((x, y) -> y.getDate().compareTo(x.getDate()))
                .collect(Collectors.toList());
    }
    public List<Message> getMessagesByUserAndDestination(String source, String destination) {
        return messageRepository.findMessageBySourceOrDestination(source, source)
                .stream()
                .filter(x -> x.getDestination().equals(destination) || x.getSource().equals(destination))
                .toList();
    }
    public Message AddMessages(Message message){
        return messageRepository.save(message);
    }
}
