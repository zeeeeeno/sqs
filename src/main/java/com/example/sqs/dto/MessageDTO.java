package com.example.sqs.dto;

import lombok.*;

/*
@Getter - 직렬화 할 때 필요
@NoArgsConstructor - 역직렬화할 때 필요
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String sender;
    private String contents;
    private String date;
}
