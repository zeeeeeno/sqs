package com.example.sqs.dto;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@ConfigurationProperties(prefix = "cloud.aws.sqs.queue")
public class AmazonSQSComponent {
    private String url;
}
