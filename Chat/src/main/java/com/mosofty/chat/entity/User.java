package com.mosofty.chat.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@AllArgsConstructor
public class User  {

    private static final long serialVersionUID = 1L;


    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean enabled = true;

    private String username;
    private String password;

    private String fullName;

    private String surname;
    private String peerId;


    private String companyName;
    public User() {
    }

    public User(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



}
