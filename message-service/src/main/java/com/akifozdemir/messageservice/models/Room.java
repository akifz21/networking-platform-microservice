package com.akifozdemir.messageservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    private String id;
    private String senderUserId;
    private String receiverUserId;

    @DBRef
    Set<Message> messages;
}
