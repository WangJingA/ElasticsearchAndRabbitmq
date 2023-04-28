package com.example.elasticsearch.Controllers;

import com.example.elasticsearch.service.impl.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("webscoket")
public class WebSocketController {
    @PostMapping("/pushMessage")
    public void pushMessage() throws IOException {
        WebSocketService.sendMessage("test","test send message");
    }
}
