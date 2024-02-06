package com.mosofty.chat.entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class MessageDeserializer implements Deserializer<Message> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }
    public MessageDeserializer() {
        // Register the JavaTimeModule for Java 8 date/time types
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public Message deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Message.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing message", e);
        }
    }

    @Override
    public void close() {
        // No resources to close
    }
}
