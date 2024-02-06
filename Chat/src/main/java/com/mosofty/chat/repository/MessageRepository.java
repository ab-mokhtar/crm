package com.mosofty.chat.repository;

import com.mosofty.chat.entity.Message;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findMessageBySourceOrDestination(String source, String destination);


}
