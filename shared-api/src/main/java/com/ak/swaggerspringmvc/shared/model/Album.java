package com.ak.swaggerspringmvc.shared.model;

import org.joda.time.LocalDate;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

public class Album extends ResourceSupport {
  private Long uid;
  private String name;
  private LocalDate releaseDate;
  private BigDecimal price;
  private String recordingLabel;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getRecordingLabel() {
    return recordingLabel;
  }

  public void setRecordingLabel(String recordingLabel) {
    this.recordingLabel = recordingLabel;
  }


  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }
}
