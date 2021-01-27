package com.example.sqs.controller;

import lombok.extern.java.Log;
import com.example.sqs.dto.MessageDto;
import org.springframework.http.HttpStatus;
import com.example.sqs.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * SQS 컨트롤러
 * - @RequiredArgsConstructor: 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성, 주로 의존성 주입 편의성을 위해서 사용되곤 합니다.
 */
@Log
@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 서버 연결 확인(테스트용)
     * @return 서버 작동 유무
     */
    @GetMapping("connection")
    public ResponseEntity<Object> isConn() {
        log.info("MessageController - isConn()");

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * 메시지 송신
     * @param message: text
     * @return 매개변수 전송 결과
     */
    @PostMapping("message/text")
    public ResponseEntity<String> sendMessageText(@RequestBody String message) {
        log.info("MessageController sendMessage1() msg: " + message);

        try {
            messageService.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 메시지 송신
     * @param message: object
     * @return 매개변수 전송 결과
     */
    @PostMapping("message/object")
    public ResponseEntity<String> sendMessageObject(@RequestBody MessageDto message) {
        log.info("MessageController sendMessage2() message: " + message);

        try {
            messageService.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 메시지 수신
     * @return 데이터 수신 여부
     */
    @GetMapping("message/text")
    public ResponseEntity<Message<String>> receiveTextMessage() {
        log.info("MessageController - receiveMessage1()");
        Message<String> message;

        try {
            message = messageService.receiveTextMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 메시지 수신
     * @return 데이터 수신 여부
     */
    @GetMapping("message/object")
    public ResponseEntity<MessageDto> receiveObjectMessage() throws NullPointerException {
        log.info("MessageController - receiveMessage2()");
        MessageDto messageDto;

        try {
            messageDto = messageService.receiveObjectMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }
}
