package com.example.sqs.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Log
@Service
public class MessageService {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageService(AmazonSQS amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
    }

    public void sendMessage(String message) {
        log.info("MessageService - sendMessage() message: " + message);

        Message<String> newMessage = MessageBuilder.withPayload(message).build();
        queueMessagingTemplate.send("paul-standard-queue", newMessage);
    }

    public void getMsg() {
        log.info("MessageService - getMsg()");

        String receiveMsg = queueMessagingTemplate.receiveAndConvert("paul-standard-queue", String.class);
        log.info("receiveMsg: " + receiveMsg);
    }

    public void postMsg(com.example.sqs.dto.Message message) {
        log.info("MessageService - receiveMsg()");

        queueMessagingTemplate.convertAndSend("paul-standard-queue", message);
    }
}
