package com.samyung.param;

public enum CustomerSortBy {
  STATUS(0),
  CREATION_DATE_TIME(1),
  ;

  private int value;

  CustomerSortBy(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public static CustomerSortBy parse(int value) {
    switch (value) {
      case 0:
        return STATUS;
      case 1:
        return CREATION_DATE_TIME;
      default:
        throw new CustomerSortBy.InvalidCustomerSortByException("Invalid customer sort by value:"+value);

    }
  }

  public static class InvalidCustomerSortByException extends IllegalArgumentException {

    public InvalidCustomerSortByException () {
      super();
    }

    public InvalidCustomerSortByException (String s) {
      super (s);
    }
  }
}
