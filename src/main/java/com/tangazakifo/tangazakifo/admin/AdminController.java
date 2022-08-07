package com.tangazakifo.tangazakifo.admin;

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
@RequestMapping("/api/admins")
public class AdminController {

  @Autowired
  AdminService adminService;

  @GetMapping("/admin/{id}")
  Admin getUser(@PathVariable Long id) {
    return adminService.findAdmin(id);
  }

  @GetMapping("/all")
  List<Admin> getAllAdmins() {
    return adminService.getAllAdmins();
  }

  @PostMapping("/admin")
  Admin createUser(@RequestBody Admin admin) {
    return adminService.createAdmin(admin);
  }

  @PutMapping("/admin")
  boolean updateUser(@RequestBody Admin admin) {
    return adminService.updateAdmin(admin);
  }

  @DeleteMapping("/admin/{id}")
  Boolean deleteUser(@PathVariable Long id) {
    return adminService.deleteAdmin(id);
  }
}
