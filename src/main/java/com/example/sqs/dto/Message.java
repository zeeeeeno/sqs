package com.example.sqs.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
public class Message {
    private String sender;
    private String contents;
    private String date;
}
