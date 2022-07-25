package com.example.insideapp.dto.util;

import com.example.insideapp.model.Message;
import com.example.insideapp.dto.MessageRequestDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CheckMessageImpl implements CheckMessage{
    @Override
    public TypeMessage getTypeMessage(MessageRequestDto messageRequestDto) {
        String message = messageRequestDto.getMessage();
        if(message!=null && !message.isEmpty()) {
            String[] strs = message.split(" ");
            if (strs.length == 2 && strs[0].equals("history")) {
                try {
                    int count = Integer.parseInt(strs[1]);
                    messageRequestDto.setCountMessage(count);
                    messageRequestDto.setTypeMessage(TypeMessage.GETMESSAGE);
                } catch (NumberFormatException e) {
                    messageRequestDto.setTypeMessage(TypeMessage.SETMESSAGE);
                }
            }else messageRequestDto.setTypeMessage(TypeMessage.SETMESSAGE);
        }else messageRequestDto.setTypeMessage(TypeMessage.SETMESSAGE);
        return messageRequestDto.getTypeMessage();
    }

    @Override
    public List<Message> getMessagesByCount(MessageRequestDto messageRequestDto) {
        messageRequestDto.getUser().getMessages().sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o2.getUpdated().compareTo(o1.getUpdated());
            }
        });
        List<Message>temp = messageRequestDto.getUser().getMessages();
        List<Message> result = new ArrayList<>();
        if(temp!=null && temp.size()>0){
            int size = temp.size() < messageRequestDto.getCountMessage() ? temp.size() : messageRequestDto.getCountMessage();
            for (int i = 0; i < size; i++) {

                result.add(temp.get(i));
            }
        }
        return result;
    }
}
