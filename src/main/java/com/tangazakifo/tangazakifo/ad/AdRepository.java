package com.tangazakifo.tangazakifo.ad;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdRepository extends CrudRepository<Ad, Long> {

  @Query(value = "SELECT * FROM ads WHERE user_id = ?1", nativeQuery = true)
  public List<Ad> findByUserId(Long id);
}
