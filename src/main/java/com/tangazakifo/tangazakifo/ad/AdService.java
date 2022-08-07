package com.tangazakifo.tangazakifo.ad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangazakifo.tangazakifo.ad_target.AdRecipient;
import com.tangazakifo.tangazakifo.ad_target.AdRecipientRepository;

@Service
public class AdService {

  @Autowired
  AdRepository adRepository;

  @Autowired
  AdRecipientRepository adRecipientRepository;

  public Ad createAd(Ad ad) {
    return adRepository.save(ad);
  }

  public Ad findAdById(Long id) {
    return adRepository.findById(id).get();
  }

  public List<Ad> findAllAds() {
    List<Ad> ads = new ArrayList<>();
    adRepository.findAll().forEach(ads::add);
    return ads;
  }

  public Boolean updateAd(Ad ad) {
    adRepository.save(ad);
    return true;
  }

  public Boolean deleteAd(Long id) {
    adRepository.deleteById(id);
    return true;
  }

  public List<AdRecipient> addRecipients(Long adId, List<AdRecipient> recipients) {
    Ad ad = adRepository.findById(adId).get();
    recipients.forEach(recipient -> {
      recipient.setAd(ad);
    });

    List<AdRecipient> savedRecipients = new ArrayList<>();
    adRecipientRepository.saveAll(recipients).forEach(savedRecipients::add);

    return savedRecipients;
  }

  public List<Ad> getAdsByUserId(Long id) {
    return adRepository.findByUserId(id);
  }
}
