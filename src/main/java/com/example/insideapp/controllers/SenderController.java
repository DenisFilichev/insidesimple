package com.example.insideapp.controllers;

import com.example.insideapp.data.UserService;
import com.example.insideapp.dto.AuthenticationRequestDto;
import com.example.insideapp.model.User;
import com.example.insideapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication and registration requests (login and reg)
 *
 * @author Denis Filichev
 * @version 1.0
 */

@RestController
public class SenderController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Autowired
    public SenderController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @RequestMapping(value = "/rest/reg", method = RequestMethod.POST)
    public ResponseEntity registration(@Valid @RequestBody AuthenticationRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user = userService.register(user);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            String password = requestDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = tokenProvider.createToken(username);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
