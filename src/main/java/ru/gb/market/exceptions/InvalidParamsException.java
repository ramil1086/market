package ru.gb.market.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidParamsException extends RuntimeException{
private List<String> messages;

public List<String> getMessages() {
    return messages;
}
    public InvalidParamsException(List<String> messages) {
       super(messages.stream().collect(Collectors.joining(", ")));
        this.messages = messages;
    }
    public InvalidParamsException(String message) {
       this(List.of(message));
    }
}
