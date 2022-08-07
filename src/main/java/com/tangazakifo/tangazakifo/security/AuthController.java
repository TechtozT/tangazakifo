package com.tangazakifo.tangazakifo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangazakifo.tangazakifo.user.User;
import com.tangazakifo.tangazakifo.user.UserService;

@RestController
@RequestMapping("/")
public class AuthController {

  @Autowired
  UserService userService;

  @PostMapping("/register")
  User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }
}
