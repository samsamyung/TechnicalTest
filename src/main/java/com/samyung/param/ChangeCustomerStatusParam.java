package com.samyung.param;

import com.samyung.entity.CustomerStatus;

public class ChangeCustomerStatusParam {
  long id;
  CustomerStatus status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CustomerStatus getStatus() {
    return status;
  }

  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "ChangeCustomerStatusParam{" +
            "id=" + id +
            ", status=" + status +
            '}';
  }
}
