package com.samyung.entity;

import com.google.gson.annotations.SerializedName;

public enum CustomerStatus {
  @SerializedName("0") PROSPECTIVE(0),
  @SerializedName("1") CURRENT(1),
  @SerializedName("2") NON_ACTIVE(2)
  ;

  private int value;

  CustomerStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public static CustomerStatus parse(int value) {
    switch (value) {
      case 0:
        return PROSPECTIVE;
      case 1:
        return CURRENT;
      case 2:
        return NON_ACTIVE;
      default:
        throw new InvalidCustomerStatusException("Invalid customer status:"+value);

    }
  }

  public static class InvalidCustomerStatusException extends IllegalArgumentException {

    public InvalidCustomerStatusException () {
      super();
    }

    public InvalidCustomerStatusException (String s) {
      super (s);
    }
  }
}
