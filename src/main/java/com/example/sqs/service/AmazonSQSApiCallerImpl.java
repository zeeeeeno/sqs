package com.example.sqs.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.example.sqs.dto.AmazonSQSComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AmazonSQSApiCallerImpl implements AmazonSQSApiCaller {
    private final AmazonSQSComponent amazonSQSComponent;

    private final AmazonSQS amazonSQS;

    public SendMessageResult sendMessage(String message) {
        return amazonSQS.sendMessage(amazonSQSComponent.getUrl(), message);
    }
}
