package com.example.insideapp.data;

import com.example.insideapp.dto.Message;
import com.example.insideapp.dto.User;
import com.example.insideapp.repository.MessageRepository;
import com.example.insideapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link MessageService} interface.
 * Wrapper for {@link MessageRepository} + business logic.
 *
 * @author Denis Filichev
 * @version 1.0
 */

@Service
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessageByUsername(User user) {
        return messageRepository.findMessagesByUser(user);
    }

    @Override
    public Message save(Message newMessage) {
        return messageRepository.save(newMessage);
    }
}
