package com.example.insideapp.repository;

import com.example.insideapp.dto.Message;
import com.example.insideapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Message}.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByUser(User user);

}
