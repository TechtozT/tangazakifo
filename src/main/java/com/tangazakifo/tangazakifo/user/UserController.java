package com.tangazakifo.tangazakifo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/all")
  List<User> getAllUsers() {

    return userService.getAllUsers();
  }

  @GetMapping("/user/{id}")
  User getUser(@PathVariable Long id) {
    return userService.findUser(id);
  }

  @PostMapping("/user")
  User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/user")
  boolean updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }

  @DeleteMapping("/user/{id}")
  Boolean deleteUser(@PathVariable Long id) {
    return userService.deleteUser(id);
  }
}
