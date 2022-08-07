package com.tangazakifo.tangazakifo.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
  @Query("select u from Admin u where u.phone = ?1")
  public Admin findByPhone(String phone);
}
