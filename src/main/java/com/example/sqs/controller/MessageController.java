package com.example.sqs.controller;

import com.example.sqs.dto.Message;
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

    @GetMapping("conn")
    public ResponseEntity isConn() {
        log.info("connect succes!");

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @PostMapping("post")
    public ResponseEntity sendMsg(@RequestBody String msg) {
        log.info("send method");

        messageService.sendMessage(msg);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity getMsg() {
        log.info("getMsg method");

        messageService.getMsg();

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @PostMapping("send")
    public ResponseEntity postMsg(@RequestBody Message message) {
        log.info("postMsg method");

        messageService.postMsg(message);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

//    @PostMapping("send")
//    public String send(@RequestBody String message) {
//        log.info("message: " + message);
//        log.info(amazonSQSApiCaller.sendMessage(message).toString()); // 테스트를 위한 콘솔 출력
//        return "OK";
//    }
}
