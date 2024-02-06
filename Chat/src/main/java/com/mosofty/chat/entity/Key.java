package com.mosofty.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Key {
    private String source;
    private String destination;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;
        return (source.equals(key.getSource()) && destination.equals(key.getDestination())) ||
                (source.equals(key.getDestination()) && destination.equals(key.getSource()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination) + Objects.hash(destination, source);
    }
}
