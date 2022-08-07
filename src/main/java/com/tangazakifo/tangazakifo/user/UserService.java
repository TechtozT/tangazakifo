package com.tangazakifo.tangazakifo.user;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User createUser(User user) {
    int strength = 10; // work factor of bcrypt
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

    user.setPassword(encodedPassword);
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  public User findUser(Long id) {
    return userRepository.findById(id).get();
  }

  public User findByPhone(String phone) {
    return userRepository.findByPhone(phone);
  }

  public boolean updateUser(User user) {
    userRepository.save(user);
    return true;
  }

  public boolean deleteUser(Long id) {
    userRepository.deleteById(id);
    return true;
  }
}
