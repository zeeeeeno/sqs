package com.example.sqs.service;

import com.example.sqs.dto.MessageDto;

public interface MessageService {
    /**
     * SQS 내에 있는 문자열 타입 메시지 받기
     */
    void receiveTextMessage();

    /**
     * SQS 내에 있는 객체 타입 메시지 받기
     */
    void receiveObjectMessage();

    /**
     * SQS 에 문자열 타입 메시지 보내
     * @param message: 보낼 메시지
     */
    void sendMessage(String message);

    /**
     * SQS 에 객체 타입 메시지 보내
     * @param message: 보낼 메시지
     */
    void sendMessage(MessageDto message);
}
