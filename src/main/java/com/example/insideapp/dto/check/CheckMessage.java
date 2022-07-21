package com.example.insideapp.dto.check;

import com.example.insideapp.dto.Message;
import com.example.insideapp.dto.MessageRequestDto;

import java.util.List;

public interface CheckMessage {

    TypeMessage getTypeMessage(MessageRequestDto messageRequestDto);

    List<Message> getMessagesByCount(MessageRequestDto messageRequestDto);
}
