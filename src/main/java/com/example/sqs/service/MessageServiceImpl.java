package com.example.sqs.service;

import java.util.Objects;
import lombok.extern.java.Log;
import com.example.sqs.dto.MessageDto;
import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;


@Log
@Service
@SuppressWarnings("rawtypes")
public class MessageServiceImpl implements MessageService {

    /**
     * Message Convertor
     */
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageServiceImpl(AmazonSQS amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
    }

    /**
     * 메시지 송신 예제1
     * @param message: text
     */
    public void sendMessage(String message) {
        log.info("MessageService - sendMessage1() message: " + message);

        Message<String> newMessage = MessageBuilder.withPayload(message).build();
        queueMessagingTemplate.send("paul-standard-queue", newMessage);
    }

    /**
     * 메시지 수신 예제1
     */
    public void receiveTextMessage() {
        log.info("MessageService - receiveMessage1()");

        Message message = queueMessagingTemplate.receive("paul-standard-queue");
        log.info("message: " + message);
    }

    /**
     * 메시지 송신 예제2
     * @param message: object
     */
    public void sendMessage(MessageDto message) {
        log.info("MessageService - sendMessage2() message: " + message);
        log.info("message: " + message.getClass().getName());

        queueMessagingTemplate.convertAndSend("paul-standard-queue", message);
    }

    /**
     * 메시지 수신 예제2
     */
    public void receiveObjectMessage() throws NullPointerException{
        log.info("MessageService - receiveMessage1()");
        MessageDto message = queueMessagingTemplate.receiveAndConvert("paul-standard-queue", MessageDto.class);
        log.info("message sender: " + Objects.requireNonNull(message).getSender());
    }
}
