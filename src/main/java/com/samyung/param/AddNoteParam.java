package com.samyung.param;

public class AddNoteParam {
  long customerId;
  String content;

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

  @Override
  public String toString() {
    return "AddNoteParam{" +
            "customerId=" + customerId +
            ", content='" + content + '\'' +
            '}';
  }
}
