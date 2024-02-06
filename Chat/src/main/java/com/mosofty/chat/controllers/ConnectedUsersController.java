package com.mosofty.chat.controllers;

import com.mosofty.chat.entity.ConnectedUsers;
import com.mosofty.chat.entity.User;
import com.mosofty.chat.services.ConnectedUsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/Connected")
public class ConnectedUsersController {
    private final ConnectedUsersService connectedUsersService;
    private final SimpMessagingTemplate template;
    @PostMapping("/add")
    public ResponseEntity<ConnectedUsers> addConnection(@RequestBody User user){
       ConnectedUsers cu= connectedUsersService.AddUserConnection(user);
       broadcastConnectedUsers(cu.getCompanyName(),cu.getUsers());
       return  ResponseEntity.ok(cu);
    }
    @MessageMapping("/sendPeerId")
    public void updatePeerId(@Payload User user) {
        try {
            // You can perform any additional logic here if needed
            // For example, you may want to store the peerId in a database or process it in some way

            // Send the peerId to the /topic/PeerId channel
            ConnectedUsers cu= connectedUsersService.AddUserConnection(user);
            broadcastConnectedUsers(cu.getCompanyName(),cu.getUsers());
            System.err.println("PeerId : " + user.getPeerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/remove")
    public ResponseEntity<ConnectedUsers> RemoveConnection(@RequestBody User user){
        ConnectedUsers cu= connectedUsersService.RemoveUserConnection(user);
        broadcastConnectedUsers(cu.getCompanyName(),cu.getUsers());
        return  ResponseEntity.ok(cu);
    }
    @GetMapping("/{company}")
    public ResponseEntity<ConnectedUsers> getUsers(@PathVariable String company){
        ConnectedUsers cu= connectedUsersService.GetConnetedByCompanyName(company);
        broadcastConnectedUsers(cu.getCompanyName(),cu.getUsers());
        return  ResponseEntity.ok(cu);
    }
    @MessageMapping("/leave")
    public void leaveChat(@Payload User user) {
        System.out.println(user.getCompanyName()+" disconnected");
        System.out.println(user.getId()+" disconnected");

//        ConnectedUsers cu= connectedUsersService.RemoveUserConnection(user);
//        broadcastConnectedUsers(cu.getCompanyName(), cu.getUsers());
    }

    private void broadcastConnectedUsers(String company,Set<User> connectedUsers) {
        template.convertAndSend("/topic/connected/"+company, connectedUsers);    }
}
