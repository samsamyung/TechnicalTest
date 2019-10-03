package com.samyung.command;

public enum CommandType {
  GET_CUSTOMERS("GET_CUSTOMERS"),
  GET_CUSTOMER_DETAILS("GET_CUSTOMER_DETAILS"),
  CHANGE_CUSTOMER_STATUS("CHANGE_CUSTOMER_STATUS"),
  GET_NOTES("GET_NOTES"),
  ADD_NOTE("ADD_NOTE"),
  EDIT_NOTE("EDIT_NOTE"),
  DELETE_NOTE("DELETE_NOTE"),
  ;

  private String value;

  CommandType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
