package com.samyung.param;

import com.samyung.entity.CustomerStatus;

public class GetCustomerDetailsCriteria {
  long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "GetCustomerDetailsCriteria{" +
            "id=" + id +
            '}';
  }
}
