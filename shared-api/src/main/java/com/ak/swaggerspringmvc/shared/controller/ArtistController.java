package com.ak.swaggerspringmvc.shared.controller;

import com.ak.swaggerspringmvc.shared.model.Artist;
import com.ak.swaggerspringmvc.shared.model.ArtistSearch;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "Artists", position = 0)
public class ArtistController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);

  @RequestMapping(value = "/artists", method = RequestMethod.GET)
  public
  @ResponseBody
  List<Artist> getAll(@ModelAttribute ArtistSearch artistSearch) {
    LOGGER.info("Search criteria: {}", artistSearch);
    List<Artist> artists = new ArrayList<Artist>();
    Artist artist = new Artist();
    artist.setFirstName("John");
    artist.setLastName("Dahlback");
    artists.add(artist);
    return artists;
  }
}
