package com.samyung.param;

public class DeleteNoteParam {
  long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "DeleteNoteParam{" +
            "id=" + id +
            '}';
  }
}
