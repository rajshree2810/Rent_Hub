package com.stackroute.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chats_database_sequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseSequence {

    @Id
    private String id;
    private int seq;

//    public DatabaseSequence() {
//    }
//
//    public DatabaseSequence(String id, int seq) {
//        this.id = id;
//        this.seq = seq;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
