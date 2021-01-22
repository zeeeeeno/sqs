package com.example.sqs.service;

import com.amazonaws.services.sqs.model.SendMessageResult;

public interface AmazonSQSApiCaller {
    SendMessageResult sendMessage(String message);
}
