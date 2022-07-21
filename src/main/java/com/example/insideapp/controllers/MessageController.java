package com.example.insideapp.controllers;

import com.example.insideapp.data.MessageService;
import com.example.insideapp.data.UserService;
import com.example.insideapp.dto.check.CheckMessage;
import com.example.insideapp.dto.check.TypeMessage;
import com.example.insideapp.dto.Message;
import com.example.insideapp.dto.MessageRequestDto;
import com.example.insideapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

/**
 * REST controller for creating or receiving messages requests (methods POST or GET)
 *
 * @author Denis Filichev
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/rest/")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final CheckMessage checkMessage;

    @Autowired
    public MessageController(MessageService messageService, UserService userService, CheckMessage checkMessage) {
        this.messageService = messageService;
        this.userService = userService;
        this.checkMessage = checkMessage;
    }

    @GetMapping(value = "messages")
    public ResponseEntity getMessage(@RequestBody MessageRequestDto messageRequestDto){
        String name = messageRequestDto.getName();
        checkMessage.getTypeMessage(messageRequestDto);
        if(messageRequestDto.getTypeMessage() == TypeMessage.GETMESSAGE) {
            User user = userService.getUserByUsername(name);
            messageRequestDto.setUser(user);
            Map<Object, Object> response = new HashMap<>();
            response.put("name", name);
            List<Message> messageList = checkMessage.getMessagesByCount(messageRequestDto);
            response.put("message", messageList.stream().map(e -> e.getTexts()).toArray(String[]::new));
            return new ResponseEntity(response, HttpStatus.OK);
        }else {
            return setMessage(messageRequestDto);
        }
    }

    @PostMapping(value = "messages")
    public ResponseEntity setMessage(@RequestBody MessageRequestDto messageRequestDto){
        ResponseEntity responseEntity;
        String name = messageRequestDto.getName();
        String text = messageRequestDto.getMessage();
        User user = userService.getUserByUsername(name);
        if(user!=null){
            Message message = new Message();
            message.setTexts(text);
            message.setCreated(Instant.now());
            message.setUpdated(Instant.now());
            message.setUser(user);
            message = messageService.save(message);
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
        }else responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
