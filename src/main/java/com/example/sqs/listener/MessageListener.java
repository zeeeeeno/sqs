package com.example.sqs.listener;

import com.example.sqs.dto.MessageDTO;
import lombok.extern.java.Log;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log
@Component
public class MessageListener {
    /**
     * ALWAYS: 메소드로 Message가 들어오면 무조건 삭제 요청을 보낸다.
     * NEVER: 절대 삭제 요청을 보내지 않는다. ACknowledgement 파라미터를 바인딩 받아 ack 메소드를 호출할 때 삭제 요청을 보낸다.
     * NO_REDRIVE: redrive policy(DLO)가 정의되지 않으면 메시지를 삭제한다.
     * ON_SUCCESS: SqsListener 어노테이션이 붙은 메소드에서 에러가 나지 않으면 메시지를 삭제한다.
     */
    @SqsListener(value = "paul-standard-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload MessageDTO message, @Headers Map<String, String> headers, Acknowledgment ack) {
        log.info("MessageListener - listen()");
        System.out.println(message.getSender());
//        ack.acknowledge();
    }
}
