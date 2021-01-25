package com.example.sqs.controller;

import lombok.extern.java.Log;
import com.example.sqs.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.sqs.service.MessageServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * SQS 컨트롤러
 * - RequiredArgsConstructor: 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성, 주로 의존성 주입 편의성을 위해서 사용되곤 합니다.
 */
@Log
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageServiceImpl messageService;

    /**
     * 서버 연결 확인
     * @return 서버 작동 유무
     */
    @GetMapping("connection")
    public ResponseEntity<Object> isConn() {
        log.info("MessageController - isConn()");

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 메시지 송신
     * @param message: text
     * @return 매개변수 전송 결과
     */
    @PostMapping("message/text")
    public ResponseEntity<String> sendMessage1(@RequestBody String message) {
        log.info("MessageController sendMessage1() msg: " + message);
        log.info("msg: " + message.getClass().getName());

        messageService.sendMessage(message);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 메시지 송신
     * @param message: object
     * @return 매개변수 전송 결과
     */
    @PostMapping("message/object")
    public ResponseEntity<String> sendMessage2(@RequestBody MessageDto message) {
        log.info("MessageController sendMessage2() message: " + message);
        log.info("message: " + message.getClass().getName());

        messageService.sendMessage(message);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 메시지 수신
     * @return 데이터 수신 여부
     */
    @GetMapping("message/text")
    public ResponseEntity<String> receiveMessage1() {
        log.info("MessageController - receiveMessage1()");

        messageService.receiveTextMessage();

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 메시지 수신
     * @return 데이터 수신 여부
     */
    @GetMapping("message/object")
    public ResponseEntity<String> receiveMessage2() throws NullPointerException {
        log.info("MessageController - receiveMessage2()");

        messageService.receiveObjectMessage();

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
