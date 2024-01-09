package com.ALGo.ALGo_server.message.controller;

import com.ALGo.ALGo_server.message.dto.MessageResponse;
import com.ALGo.ALGo_server.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/test-message")
    public void testMessage() {
        try {
            // MessageService의 message 메서드 호출
            messageService.message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
