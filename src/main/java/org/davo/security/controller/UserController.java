package org.davo.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.davo.security.identity.User;
import org.davo.security.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(User user){
        log.info("UserController.createUser");
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        log.info("UserController.getAllUsers");
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
