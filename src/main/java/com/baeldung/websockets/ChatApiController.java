package com.baeldung.websockets;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final Gson gson = new Gson();


    public ChatApiController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/chat")
    public ResponseEntity<Object> getAllPublicUsers(@RequestBody(required = false) OutputMessage outputMessage) {
        OutputMessage outputMessage1 = new OutputMessage(outputMessage.getFrom(), outputMessage.getText(), new SimpleDateFormat("HH:mm").format(new Date()));
        simpMessagingTemplate.convertAndSend("/topic/messages", gson.toJson(outputMessage1));
        return new ResponseEntity<>(gson.toJson(outputMessage1), HttpStatus.OK);
    }

}
