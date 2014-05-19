package com.ak.swaggerspringmvc.shared.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class Artist extends ResourceSupport {
  private String firstName;
  private String lastName;
  private List<Album> albums = new ArrayList<Album>();

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Album> getAlbums() {
    return albums;
  }

  public void addAlbum(Album a){
    this.albums.add(a);
  }
  public void setAlbums(List<Album> albums) {
    this.albums = albums;
  }
}
