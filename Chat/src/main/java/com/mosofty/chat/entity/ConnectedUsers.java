package com.mosofty.chat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.HashSet;
import java.util.Set;

@Document(collection="connectedusers")
@Data
public class ConnectedUsers {
    @Id
    private String companyName;
    private Set<User> users;

    public ConnectedUsers(String companyName) {
        this.companyName = companyName;
        users=new HashSet<>();
    }
}
