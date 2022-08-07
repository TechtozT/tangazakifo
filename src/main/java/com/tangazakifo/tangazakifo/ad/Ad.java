package com.tangazakifo.tangazakifo.ad;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tangazakifo.tangazakifo.ad_target.AdRecipient;
import com.tangazakifo.tangazakifo.user.User;

@Entity
@Table(name = "ads")
public class Ad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String lateName;
  private String lateAddress;
  private String placeOfDeath;
  private Date dateOfDeath;
  private String funeralLocation;
  private Date funeralDate;
  private String comment;
  private Integer broadcastFrequency;
  private String deathCertificatePath;
  private Date createdAt;
  private Boolean isPaid;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<AdRecipient> AdRecipients = new ArrayList<>();

  public Ad() {
  }

  public Ad(String lateName, String lateAddress, String placeOfDeath,
      Date dateOfDeath, String funeralLocation, Date funeralDate, String comment, Integer broadcastFrequency,
      String deathCertificatePath, Date createdAt, Boolean isPaid) {
    this.lateName = lateName;
    this.lateAddress = lateAddress;
    this.placeOfDeath = placeOfDeath;
    this.dateOfDeath = dateOfDeath;
    this.funeralLocation = funeralLocation;
    this.funeralDate = funeralDate;
    this.comment = comment;
    this.broadcastFrequency = broadcastFrequency;
    this.deathCertificatePath = deathCertificatePath;
    this.createdAt = createdAt;
    this.isPaid = isPaid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLateName() {
    return lateName;
  }

  public void setLateName(String lateName) {
    this.lateName = lateName;
  }

  public String getLateAddress() {
    return lateAddress;
  }

  public void setLateAddress(String lateAddress) {
    this.lateAddress = lateAddress;
  }

  public String getPlaceOfDeath() {
    return placeOfDeath;
  }

  public void setPlaceOfDeath(String placeOfDeath) {
    this.placeOfDeath = placeOfDeath;
  }

  public Date getDateOfDeath() {
    return dateOfDeath;
  }

  public void setDateOfDeath(Date dateOfDeath) {
    this.dateOfDeath = dateOfDeath;
  }

  public String getFuneralLocation() {
    return funeralLocation;
  }

  public void setFuneralLocation(String funeralLocation) {
    this.funeralLocation = funeralLocation;
  }

  public Date getFuneralDate() {
    return funeralDate;
  }

  public void setFuneralDate(Date funeralDate) {
    this.funeralDate = funeralDate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Integer getBroadcastFrequency() {
    return broadcastFrequency;
  }

  public void setBroadcastFrequency(Integer broadcastFrequency) {
    this.broadcastFrequency = broadcastFrequency;
  }

  public String getDeathCertificatePath() {
    return deathCertificatePath;
  }

  public void setDeathCertificatePath(String deathCertificatePath) {
    this.deathCertificatePath = deathCertificatePath;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getIsPaid() {
    return isPaid;
  }

  public void setIsPaid(Boolean isPaid) {
    this.isPaid = isPaid;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
