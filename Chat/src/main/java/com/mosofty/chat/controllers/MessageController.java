package com.mosofty.chat.controllers;

import com.mosofty.chat.entity.Key;
import com.mosofty.chat.entity.Message;
import com.mosofty.chat.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    @PostMapping("/getDisc")
    public List<Message> getDiscussions(@RequestBody String user){
        return messageService.getDiscusions(user);
    }
    @PostMapping("/getmessages")
    public List<Message> getMessage(@RequestBody Key request){
        return messageService.getMessagesByUserAndDestination(request.getSource(),request.getDestination());
    }
}
