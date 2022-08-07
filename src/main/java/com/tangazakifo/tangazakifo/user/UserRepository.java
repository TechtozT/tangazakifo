package com.tangazakifo.tangazakifo.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  @Query("select u from User u where u.phone = ?1")
  public User findByPhone(String phone);
}
