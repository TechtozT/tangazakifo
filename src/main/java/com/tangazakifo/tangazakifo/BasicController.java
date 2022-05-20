package com.tangazakifo.tangazakifo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class BasicController {

  @GetMapping
  String hello() {
    return "Hello There";
  }

  @GetMapping("/{id}")
  String getById(@PathVariable String id) {
    System.out.println(id);
    return "You requested resource with id " + id;
  }
}
