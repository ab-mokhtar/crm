package com.mosofty.chat.controllers;


import com.mosofty.chat.entity.MessageRequest;
import com.mosofty.chat.services.KafkaListenerAutomation;
import com.mosofty.chat.services.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/messages")
public class MainController {
//    private  final KafkaProducer kafkaProducer;
//    KafkaListenerAutomation kafkaListenerAutomation;
//
//    @GetMapping("/start/{id}")
//    public void start(@PathVariable("id") String listenerId) {
//        kafkaListenerAutomation.startListener(listenerId);
//    }
//
//    @GetMapping("/stop/{id}")
//    public void stop(@PathVariable("id") String listenerId) {
//        kafkaListenerAutomation.stopListener(listenerId);
//    }
//    @PostMapping
//        public ResponseEntity<String>sendMessage(@RequestBody MessageRequest msg){
//        kafkaListenerAutomation.startListener(msg.getListenerId());
//        kafkaProducer.sendMessage(msg.getMessage(), msg.getListenerId());
//    return ResponseEntity.ok("message envoyée");
//}
//@GetMapping
//    public ResponseEntity<String>getMessage(){
//    return  ResponseEntity.ok("test");
//}

}
