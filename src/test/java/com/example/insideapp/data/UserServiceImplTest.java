package com.example.insideapp.data;


import com.example.insideapp.model.User;
import com.example.insideapp.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private User user;

    @MockBean
    public UserRepository userRepositoryMock;

    @Autowired
    public UserServiceImplTest(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("Boby");
        user.setPassword("123qwe");

    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void register() {
        String username = user.getUsername();
        String password = user.getPassword();
        User checkUser = new User();
        checkUser.setUsername(user.getUsername());
        checkUser.setPassword(passwordEncoder.encode(user.getPassword()));
        assertNotNull(checkUser);
        assertTrue(passwordEncoder.matches(password, checkUser.getPassword()));
        assertTrue(CoreMatchers.is(user.getUsername()).matches(username));
        userService.register(user);
        Mockito.verify(userRepositoryMock, Mockito.times(1))
                .save(Mockito.any(User.class));
    }
}