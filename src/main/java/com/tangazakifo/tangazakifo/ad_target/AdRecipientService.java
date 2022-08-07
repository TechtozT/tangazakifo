package com.tangazakifo.tangazakifo.ad_target;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdRecipientService {

  @Autowired
  AdRecipientRepository adRecipientRepository;

  public List<AdRecipient> findAllAdRecipients(Long id) {
    return adRecipientRepository.findByAdId(id);
  }
}
