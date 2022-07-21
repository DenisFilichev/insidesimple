package com.example.insideapp.data;

import com.example.insideapp.dto.Message;
import com.example.insideapp.dto.User;

import java.util.List;

/**
 * Service interface for class {@link Message}.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public interface MessageService {

    List<Message> getAll();

    List<Message> getMessageByUsername(User user);

    Message save(Message newMessage);
}
