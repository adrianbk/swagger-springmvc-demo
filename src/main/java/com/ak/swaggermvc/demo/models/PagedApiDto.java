package com.ak.swaggermvc.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

import java.util.Collection;
import java.util.List;

public class PagedApiDto<T> extends PagedResources<T> {
  public PagedApiDto(Collection<T> content, PageMetadata metadata, Link... links) {
    super(content, metadata, links);
  }

  public PagedApiDto(PagedResources<T> pagedResources) {
    super(pagedResources.getContent(), pagedResources.getMetadata(), pagedResources.getLinks());
  }

  @Override
  @JsonProperty("_links")
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public List<Link> getLinks() {
    return super.getLinks();
  }

  @Override
  @JsonProperty("items")
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public Collection<T> getContent() {
    return super.getContent();
  }
}
