package com.example.sqs.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;

@Configuration
public class SQSConfig {
    @Bean
    SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync);
        factory.setMaxNumberOfMessages(10);
        factory.setWaitTimeOut(20); // 긴 폴링 설정
        return factory;
    }
}
