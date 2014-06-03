package com.ak.swaggerspringmvc.shared.controller;

import com.ak.swaggerspringmvc.shared.model.Album;
import com.ak.swaggerspringmvc.shared.model.Artist;
import com.wordnik.swagger.annotations.Api;
import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/music")
@Api(value = "Albums", position = 1)
public class AlbumController {

  private static AtomicLong counter = new AtomicLong(1);
  private static Map<Long, Album> albums = new HashMap<Long, Album>();

  @RequestMapping(value = "/albums", method = RequestMethod.GET)
  public
  @ResponseBody
  List<Album> getAll() {
    return new ArrayList(albums.values());
  }

  @RequestMapping(value = "/albums/{id}", method = RequestMethod.GET)
  public
  @ResponseBody
  ResponseEntity<Album> findById(@PathVariable Long id) {
    return new ResponseEntity<Album>(albums.get(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/albums", method = RequestMethod.POST)
  public
  @ResponseBody
  ResponseEntity add(@RequestBody Album album) {
    album.setUid(counter.getAndIncrement());
    albums.put(album.getUid(), album);
    return new ResponseEntity<Album>(album, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/albums", method = RequestMethod.PUT)
  public
  @ResponseBody
  ResponseEntity update(@RequestBody Album album) {
    albums.put(album.getUid(), album);
    return new ResponseEntity<Album>(album, HttpStatus.NO_CONTENT);
  }

  static{
    Album neverMind = new Album();
    neverMind.setName("Nevermind");
    neverMind.setPrice(new BigDecimal(20.00));
    neverMind.setRecordingLabel("DGC Records");
    neverMind.setReleaseDate(new LocalDate(1991, 9, 24));
    neverMind.setUid(counter.getAndIncrement());

    Artist kurt = new Artist();
    kurt.setFirstName("Kurt");
    kurt.setLastName("Cobain");
    kurt.addAlbum(neverMind);

    albums.put(neverMind.getUid(), neverMind);
  }
}
