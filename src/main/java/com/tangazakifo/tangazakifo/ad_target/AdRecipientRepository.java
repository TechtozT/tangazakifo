package com.tangazakifo.tangazakifo.ad_target;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdRecipientRepository extends CrudRepository<AdRecipient, Long> {

  @Query(value = "SELECT * FROM ads_recipients WHERE ad_id = ?1", nativeQuery = true)
  public List<AdRecipient> findByAdId(Long id);
}
