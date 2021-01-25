package com.example.sqs.controller;

import com.example.sqs.dto.MessageDTO;
import com.example.sqs.service.AmazonSQSApiCaller;
import com.example.sqs.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * - RequiredArgsConstructor
 *  1. 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
 *  2. 주로 의존성 주입 편의성을 위해서 사용되곤 합니다.
 */
@Log
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final AmazonSQSApiCaller amazonSQSApiCaller;

    /**
     * 서버 연결 확인
     * @return
     */
    @GetMapping("conn")
    public ResponseEntity isConn() {
        log.info("MessageController - isConn()");

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * 메시지 송신 예제 1
     * @param msg: text
     * @return
     */
    @PostMapping("msg1")
    public ResponseEntity sendMessage1(@RequestBody String msg) {
        log.info("MessageController sendMessage1() msg: " + msg);
        log.info("msg: " + msg.getClass().getName());

        messageService.sendMessage1(msg);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * 메시지 송신 예제2
     * @param message: object
     * @return
     */
    @PostMapping("msg2")
    public ResponseEntity sendMessage2(@RequestBody MessageDTO message) {
        log.info("MessageController sendMessage2() message: " + message);
        log.info("message: " + message.getClass().getName());

        messageService.sendMessage2(message);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * 메시지 수신 예제 1
     * @return
     */
    @GetMapping("msg1")
    public ResponseEntity receiveMessage1() {
        log.info("MessageController - receiveMessage1()");

        messageService.receiveMessage1();

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * 메시지 수신 예제 2
     * @return
     */
    @GetMapping("msg2")
    public ResponseEntity receiveMessage2() {
        log.info("MessageController - receiveMessage2()");

        messageService.receiveMessage2();

        return new ResponseEntity("OK", HttpStatus.OK);
    }

//    @PostMapping("send")
//    public String send(@RequestBody String message) {
//        log.info("message: " + message);
//        log.info(amazonSQSApiCaller.sendMessage(message).toString()); // 테스트를 위한 콘솔 출력
//        return "OK";
//    }
}
