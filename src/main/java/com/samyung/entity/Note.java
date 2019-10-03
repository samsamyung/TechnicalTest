package com.samyung.entity;

import java.util.Date;

public class Note {
  long id;
  long customerId;
  String content;
  long creationDateTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(long creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  @Override
  public String toString() {
    return "Note{" +
            "id=" + id +
            ", customerId=" + customerId +
            ", content='" + content + '\'' +
            ", creationDateTime=" + creationDateTime +
            '}';
  }
}
