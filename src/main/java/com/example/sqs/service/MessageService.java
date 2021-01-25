package com.example.sqs.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.example.sqs.dto.MessageDTO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Log
@Service
public class MessageService {

    /**
     * Message Convertor
     */
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageService(AmazonSQS amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
    }

    /**
     * 메시지 송신 예제1
     * @param message: text
     */
    public void sendMessage1(String message) {
        log.info("MessageService - sendMessage1() message: " + message);

        Message<String> newMessage = MessageBuilder.withPayload(message).build();
        queueMessagingTemplate.send("paul-standard-queue", newMessage);
    }

    /**
     * 메시지 수신 예제1
     */
    public void receiveMessage1() {
        log.info("MessageService - receiveMessage1()");

        Message message = queueMessagingTemplate.receive("paul-standard-queue");
        log.info("message: " + message);
    }

    /**
     * 메시지 송신 예제2
     * @param message: object
     */
    public void sendMessage2(MessageDTO message) {
        log.info("MessageService - sendMessage2() message: " + message);
        log.info("message: " + message.getClass().getName());

        queueMessagingTemplate.convertAndSend("paul-standard-queue", message);
    }

    /**
     * 메시지 수신 예제2
     */
    public void receiveMessage2() {
        log.info("MessageService - receiveMessage1()");

        MessageDTO message = queueMessagingTemplate.receiveAndConvert("paul-standard-queue", MessageDTO.class);
        log.info("message sender: " + message.getSender());
    }
}
