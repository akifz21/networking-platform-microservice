package com.akifozdemir.messageservice.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "messages")
public class Message {
    private String message;
    private int roomId;

    @DBRef
    Room room;
}
