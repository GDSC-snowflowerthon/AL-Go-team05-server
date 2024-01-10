package com.ALGo.ALGo_server.message.controller;

import com.ALGo.ALGo_server.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/testMessage")
    public void getMessage() {
        try {
            messageService.message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
