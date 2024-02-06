package com.mosofty.chat.repository;

import com.mosofty.chat.entity.ConnectedUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectedUsersRepository extends MongoRepository<ConnectedUsers,String> {
}
