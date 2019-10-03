package com.samyung.param;

public class EditNoteParam {
  long id;
  String content;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "EditNoteParam{" +
            "id=" + id +
            ", content='" + content + '\'' +
            '}';
  }
}
