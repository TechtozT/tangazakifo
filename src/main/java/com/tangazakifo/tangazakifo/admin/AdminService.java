package com.tangazakifo.tangazakifo.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;

  public Admin createAdmin(Admin user) {
    return adminRepository.save(user);
  }

  public List<Admin> getAllAdmins() {
    List<Admin> admins = new ArrayList<>();
    adminRepository.findAll().forEach(admins::add);
    return admins;
  }

  public Admin findAdmin(Long id) {
    return adminRepository.findById(id).get();
  }

  public boolean updateAdmin(Admin user) {
    adminRepository.save(user);
    return true;
  }

  public boolean deleteAdmin(Long id) {
    adminRepository.deleteById(id);
    return true;
  }
}
