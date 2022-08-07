package com.tangazakifo.tangazakifo.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangazakifo.tangazakifo.ad_target.AdRecipient;
import com.tangazakifo.tangazakifo.ad_target.AdRecipientService;
import com.tangazakifo.tangazakifo.security.Authenticated;
import com.tangazakifo.tangazakifo.user.User;
import com.tangazakifo.tangazakifo.user.UserService;

@RestController
@RequestMapping("/api/ads")
public class AdController {

  @Autowired
  AdService adService;

  @Autowired
  UserService userService;

  @Autowired
  AdRecipientService adRecipientService;

  @GetMapping("/all")
  public List<Ad> getAllAds() {
    String phone = Authenticated.getUsername();
    User user = userService.findByPhone(phone);
    return adService.getAdsByUserId(user.getId());
  }

  @GetMapping("/ad/{id}")
  public Ad getAd(@PathVariable Long id) {
    return adService.findAdById(id);
  }

  @PostMapping("/create")
  public Ad createAd(@RequestBody Ad ad) {
    // Make sure that add is by default not paid so user may not try to alter it
    // while posting.
    ad.setIsPaid(false);
    String phone = Authenticated.getUsername();
    User user = userService.findByPhone(phone);
    ad.setUser(user);

    return adService.createAd(ad);
  }

  @PutMapping("/ad")
  public Boolean updateAd(@RequestBody Ad ad) {
    String phone = Authenticated.getUsername();
    User user = userService.findByPhone(phone);
    ad.setUser(user);
    return adService.updateAd(ad);
  }

  @PostMapping("delete-add")
  public List<Ad> getAllAd() {
    return adService.findAllAds();
  }

  /**
   * Post recipients for a specified ad of particular user.
   * 
   * @param adId
   * @param recipients
   * @return
   */
  @PostMapping("/recipients/{adId}")
  public ResponseEntity<List<AdRecipient>> addRecipients(@PathVariable Long adId,
      @RequestBody List<AdRecipient> recipients) {

    String phone = Authenticated.getUsername();

    Ad ad = adService.findAdById(adId);
    if (ad.getUser().getPhone() != phone) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(adService.addRecipients(adId, recipients), HttpStatus.OK);
  }

  @GetMapping("/recipients/{adId}")
  public List<AdRecipient> getAllAdRecipients(@PathVariable Long adId) {
    return adRecipientService.findAllAdRecipients(adId);
  }

  @GetMapping("/user_ads/{userId}")
  public List<Ad> getUserAds(@PathVariable Long userId) {
    return adService.getAdsByUserId(userId);
  }
}
