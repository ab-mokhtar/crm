package com.mosofty.chat.services;

import com.mosofty.chat.entity.ConnectedUsers;
import com.mosofty.chat.entity.User;
import com.mosofty.chat.repository.ConnectedUsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ConnectedUsersService {
    private  final ConnectedUsersRepository connectedUsersRepository;
    public Set<User> getConnectedUsers(String company){
        return connectedUsersRepository.findById(company).orElseThrow().getUsers();

    }
    public ConnectedUsers AddUserConnection(User user){
        ConnectedUsers c=connectedUsersRepository.findById(user.getCompanyName()).orElse(new ConnectedUsers(user.getCompanyName()));
        Set<User> users=c.getUsers();
       users= users.stream().filter(x->!x.getUsername().equals(user.getUsername())).collect(Collectors.toSet());
        users.add(user);
        c.setUsers(users);
        return connectedUsersRepository.save(c);

    }
    public ConnectedUsers RemoveUserConnection(User user){
        ConnectedUsers c=connectedUsersRepository.findById(user.getCompanyName()).orElseThrow();
        Set<User> users=c.getUsers();
        users.add(user);
        c.setUsers(users);
        return connectedUsersRepository.save(c);

    }
    public ConnectedUsers GetConnetedByCompanyName(String company){
        return connectedUsersRepository.findById(company).orElseThrow();
    }
}
