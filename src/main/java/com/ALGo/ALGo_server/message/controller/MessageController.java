package com.ALGo.ALGo_server.message.controller;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.message.dto.MessageResponse;
import com.ALGo.ALGo_server.message.service.MessageService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/disaster")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public MessageResponse getMessage(@AuthenticationPrincipal User user) throws IOException, ParseException {

        return messageService.message(user);
    }
}
