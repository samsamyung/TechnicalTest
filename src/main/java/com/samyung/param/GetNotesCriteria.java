package com.samyung.param;

import com.samyung.entity.CustomerStatus;

public class GetNotesCriteria {
  long customerId;

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  @Override
  public String toString() {
    return "GetNotesCriteria{" +
            "customerId=" + customerId +
            '}';
  }
}
